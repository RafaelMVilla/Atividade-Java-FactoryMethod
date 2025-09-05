// factory/FactoryPayPal.java
package factory;

import pagamento.Pagamento;
import pagamento.PagamentoPayPal;

public class FactoryPayPal extends PagamentoFactory {
    private final String email;
    public FactoryPayPal(String email) { this.email = email; }

    @Override
    public Pagamento criarPagamento() {
        return new PagamentoPayPal(email);
    }
}
