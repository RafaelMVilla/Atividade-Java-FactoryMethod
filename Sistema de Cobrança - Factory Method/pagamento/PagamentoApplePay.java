package pagamento;

public class PagamentoApplePay implements Pagamento {
    private final String deviceId;

    public PagamentoApplePay(String deviceId) {
        this.deviceId = deviceId;
    }

    private boolean validarDevice() {
        // Regra simples: pelo menos 6 caracteres
        return deviceId != null && deviceId.trim().length() >= 6;
    }

    @Override
    public String processarPagamento(double valor) {
        if (valor <= 0) return "Erro: valor inválido.";
        if (!validarDevice()) return "Erro: Apple Pay - dispositivo inválido (mín. 6 caracteres).";
        return "Pagamento via Apple Pay aprovado. Valor: R$ " + String.format("%.2f", valor);
    }
}
