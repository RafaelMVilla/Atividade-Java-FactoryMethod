package factory;

import pagamento.Pagamento;
import pagamento.PagamentoApplePay;

public class FactoryApplePay extends PagamentoFactory {
    private final String deviceId;

    public FactoryApplePay(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public Pagamento criarPagamento() {
        return new PagamentoApplePay(deviceId);
    }
}
