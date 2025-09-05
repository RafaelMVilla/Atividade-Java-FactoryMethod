package pagamento;

public class PagamentoCartaoCredito implements Pagamento{

    private final String numeroCartao;

    public PagamentoCartaoCredito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    private boolean validarNumero() {
        return numeroCartao != null && numeroCartao.matches("\\d{16}");
    }

    @Override
    public String processarPagamento(double valor) {
        
        if (valor <= 0) {
            return "Valor de pagamento inválido.";
        }
        if (!validarNumero()) {
            return "Número de cartão inválido. (Precisa ter 16 dígitos numéricos)";
            
        }
        return "Pagamento com Cartão aprovado. Valor: R$ " + String.format("%.2f", valor);
    }
}
