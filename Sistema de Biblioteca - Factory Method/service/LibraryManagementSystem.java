package service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import factory.FactoryBook;
import factory.FactoryDigitalMedia;
import factory.FactoryMagazine;
import materials.iBorrowable;
import materials.absLibraryItem;

public class LibraryManagementSystem {

    private enum Tipo { LIVRO, REVISTA, MIDIA }

    private final Map<Tipo, LinkedHashMap<Integer, iBorrowable>> catalogo = new LinkedHashMap<>();
    private final Map<Tipo, LinkedHashMap<Integer, String>> rotulos = new LinkedHashMap<>();

    public static void main(String[] args) {
        new LibraryManagementSystem().run();
    }

    public LibraryManagementSystem() {
        for (Tipo t : Tipo.values()) {
            catalogo.put(t, new LinkedHashMap<>());
            rotulos.put(t, new LinkedHashMap<>());
        }

        // Seeds via FACTORY (mantém estado no loop)
        catalogo.get(Tipo.LIVRO).put(1, new FactoryBook("999").create());
        rotulos.get(Tipo.LIVRO).put(1, "Livro - O Senhor dos Aneis");

        catalogo.get(Tipo.REVISTA).put(1, new FactoryMagazine("123").create());
        rotulos.get(Tipo.REVISTA).put(1, "Revista - Super Interessante");

        catalogo.get(Tipo.MIDIA).put(1, new FactoryDigitalMedia("mp4").create());
        rotulos.get(Tipo.MIDIA).put(1, "Midia Digital - Aula de Padroes de Projeto");
    }

    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                imprimirMenuPrincipal();
                int opc = lerOpcao(sc, "Escolha a categoria (0 para sair): ", 0, 3);
                if (opc == 0) {
                    System.out.println("\nSaindo. Obrigado!");
                    break;
                }

                Tipo tipo = switch (opc) {
                    case 1 -> Tipo.LIVRO;
                    case 2 -> Tipo.REVISTA;
                    case 3 -> Tipo.MIDIA;
                    default -> throw new IllegalStateException("Opcao inesperada");
                };

                if (catalogo.get(tipo).isEmpty()) {
                    System.out.println("\nNao ha itens cadastrados nessa categoria.\n");
                    continue;
                }

                imprimirSubmenuItens(tipo);
                int qtd = catalogo.get(tipo).size();
                int idx = lerOpcao(sc, "Escolha o item (0 para voltar): ", 0, qtd);
                if (idx == 0) continue;

                iBorrowable item = catalogo.get(tipo).get(idx);
                String rotulo = rotulos.get(tipo).get(idx);

                // Ação
                System.out.println("""
                        
                        Acao:
                        1 - Emprestar
                        2 - Devolver
                        """);
                int acao = lerOpcao(sc, "Escolha a acao (1/2): ", 1, 2);

                boolean ok = (acao == 1) ? item.borrow() : item.returnItem();

                // ======== Saída final (apenas a mensagem pedida) ========
                String titulo = obterTitulo(item);
                String nomeTipo = nomeTipoSingular(tipo);
                if (ok) {
                    String verbo = (acao == 1) ? "emprestado" : "devolvido";
                    System.out.printf("%s '%s' %s com sucesso.%n%n", nomeTipo, titulo, verbo);
                } else {
                    // caso falhe, uma linha curta e objetiva
                    if (acao == 1) {
                        System.out.printf("Nao foi possivel emprestar '%s' (ja emprestado).%n%n", titulo);
                    } else {
                        System.out.printf("Nao foi possivel devolver '%s' (ja disponivel).%n%n", titulo);
                    }
                }
                // ========================================================

                System.out.print("Pressione ENTER para voltar ao menu principal...");
                sc.nextLine();
            }
        }
    }

    // ================== UI helpers ==================

    private void imprimirMenuPrincipal() {
        System.out.println();
        System.out.println("+========================================+");
        System.out.println("|       SISTEMA DE BIBLIOTECA - MENU     |");
        System.out.println("+========================================+");
        System.out.println("| 1 - Livro                              |");
        System.out.println("| 2 - Revista                            |");
        System.out.println("| 3 - Midia Digital                      |");
        System.out.println("| 0 - Sair                               |");
        System.out.println("+========================================+");
    }

    private void imprimirSubmenuItens(Tipo tipo) {
        System.out.println();
        System.out.println("+----------------------------------------+");
        System.out.printf("|   ITENS - %s%n", nomeTipo(tipo));
        System.out.println("+----------------------------------------+");
        rotulos.get(tipo).forEach((i, label) -> System.out.printf("| %d - %s%n", i, label));
        System.out.println("| 0 - Voltar                             |");
        System.out.println("+----------------------------------------+");
    }

    private String nomeTipo(Tipo t) {
        return switch (t) {
            case LIVRO -> "LIVROS";
            case REVISTA -> "REVISTAS";
            case MIDIA -> "MIDIA DIGITAL";
        };
    }

    private String nomeTipoSingular(Tipo t) {
        return switch (t) {
            case LIVRO -> "Livro";
            case REVISTA -> "Revista";
            case MIDIA -> "Midia Digital";
        };
    }

    private int lerOpcao(Scanner sc, String prompt, int min, int max) {
        System.out.print(prompt);
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.print("Invalido. Digite um numero entre " + min + " e " + max + ": ");
        }
    }

    private String obterTitulo(iBorrowable item) {
        // como a interface nao tem getTitle(), obtemos via classe base
        if (item instanceof absLibraryItem a) {
            return a.getTitle();
        }
        return "(sem titulo)";
    }
}
