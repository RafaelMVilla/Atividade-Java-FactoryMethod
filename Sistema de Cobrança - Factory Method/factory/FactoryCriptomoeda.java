// factory/FactoryCriptomoeda.java
package factory;

import pagamento.Pagamento;
import pagamento.PagamentoCriptomoeda;

public class FactoryCriptomoeda extends PagamentoFactory {
    private final double saldoInicial;
    public FactoryCriptomoeda(double saldoInicial) { this.saldoInicial = saldoInicial; }

    @Override
    public Pagamento criarPagamento() {
        return new PagamentoCriptomoeda(saldoInicial);
    }
}
