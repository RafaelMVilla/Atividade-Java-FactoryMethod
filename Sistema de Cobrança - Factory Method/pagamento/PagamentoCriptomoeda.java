// pagamento/PagamentoCriptomoeda.java
package pagamento;

public class PagamentoCriptomoeda implements Pagamento {
    private double saldoCarteira;

    public PagamentoCriptomoeda(double saldoInicial) {
        this.saldoCarteira = saldoInicial;
    }

    private boolean temSaldo(double valor) {
        return saldoCarteira >= valor;
    }

    @Override
    public String processarPagamento(double valor) {
        if (valor <= 0) return "Erro: valor inválido.";
        if (!temSaldo(valor)) {
            return "Erro: saldo insuficiente. Saldo: R$ " + String.format("%.2f", saldoCarteira);
        }
        saldoCarteira -= valor;
        return "Pagamento via Cripto aprovado. Valor: R$ " + String.format("%.2f", valor)
             + " | Saldo restante: R$ " + String.format("%.2f", saldoCarteira);
    }
}
