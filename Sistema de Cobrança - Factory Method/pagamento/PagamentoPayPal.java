// pagamento/PagamentoPayPal.java
package pagamento;

import java.util.Set;

public class PagamentoPayPal implements Pagamento {
    private final String email;
    private static final Set<String> CONTAS_VALIDAS = Set.of(
        "user@example.com", "rafael.march.v@gmail.com", "cliente@empresa.com"
    );

    public PagamentoPayPal(String email) {
        this.email = email;
    }

    private boolean contaVinculada() {
        return email != null && email.contains("@") && CONTAS_VALIDAS.contains(email.toLowerCase());
    }

    @Override
    public String processarPagamento(double valor) {
        if (valor <= 0) return "Erro: valor inválido.";
        if (!contaVinculada()) return "Erro: conta PayPal não vinculada ao e-mail informado.";
        return "Pagamento via PayPal aprovado. Valor: R$ " + String.format("%.2f", valor);
    }
}
