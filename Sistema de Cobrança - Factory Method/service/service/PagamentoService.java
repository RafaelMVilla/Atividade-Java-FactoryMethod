package service;

import java.util.Scanner;

import factory.FactoryCartaoCredito;
import factory.FactoryCriptomoeda;
import factory.FactoryPayPal;
import factory.PagamentoFactory; 
import factory.FactoryApplePay;
import pagamento.Pagamento;

public class PagamentoService {

    public static void main(String[] args) {
        new PagamentoService().menuLoop();
    }

    private void menuLoop() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                imprimirMenu();
                int opcao = lerOpcao(sc);

                if (opcao == 0) {
                    System.out.println("\nSaindo. Obrigado!");
                    break;
                }

                double valor = lerValor(sc, "\nQual o valor da compra total? R$ ");

                PagamentoFactory factory = null;

                switch (opcao) {
                    case 1 -> { // Cartão
                        String numero = lerString(sc, "Informe o número do cartão (16 dígitos, apenas números): ");
                        while (!numero.matches("\\d{16}")) {
                            numero = lerString(sc, "Cartão inválido. Digite novamente (16 dígitos): ");
                        }
                        factory = new FactoryCartaoCredito(numero);
                    }
                    case 2 -> { // PayPal
                        String email = lerString(sc, "Informe o e-mail do PayPal: ");
                        factory = new FactoryPayPal(email);
                    }
                    case 3 -> { // Cripto
                        double saldo = lerValor(sc, "Informe o SALDO disponível na carteira (R$): ");
                        factory = new FactoryCriptomoeda(saldo);
                    }
                    case 4 -> { // Apple Pay  <-- NOVO
                        String deviceId = lerString(sc, "Informe o ID do dispositivo Apple Pay (mín. 6 caracteres): ");
                        while (deviceId.trim().length() < 6) {
                            deviceId = lerString(sc, "Inválido. Digite o ID (mín. 6 caracteres): ");
                        }
                        factory = new FactoryApplePay(deviceId);
                    }
                    default -> {
                        System.out.println("Opção inválida.");
                        continue;
                    }
                }


                Pagamento pagamento = factory.criarPagamento();
                String resultado = pagamento.processarPagamento(valor);

                System.out.println("\n================= RESULTADO =================");
                System.out.println(resultado);
                System.out.println("=============================================\n");
            }
        }
    }

    private void imprimirMenu() {
        System.out.println("+-------------------------------------------+");
        System.out.println("|      ESCOLHA A FORMA DE PAGAMENTO         |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 1 - Cartão de Crédito                     |");
        System.out.println("| 2 - PayPal                                |");
        System.out.println("| 3 - Criptomoeda                           |");
        System.out.println("| 4 - Apple Pay                             |");
        System.out.println("| 0 - Sair                                  |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Digite a opção (0/1/2/3/4): ");
    }

    private int lerOpcao(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            if (s.matches("[0-4]")) return Integer.parseInt(s); // <-- ajustado
            System.out.print("Opção inválida. Digite 0, 1, 2, 3 ou 4: ");
        }
    }


    private String lerString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        while (s.isEmpty()) {
            System.out.print("Entrada vazia. Tente novamente: ");
            s = sc.nextLine().trim();
        }
        return s;
    }

    private double lerValor(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (true) {
            String s = sc.nextLine().trim().replace(",", ".");
            try {
                double v = Double.parseDouble(s);
                if (v > 0) return v;
            } catch (NumberFormatException ignored) {}
            System.out.print("Valor inválido. Digite um número maior que zero: R$ ");
        }
    }
}
