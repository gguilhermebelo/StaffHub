package domain.relatorio;
public class RelatorioPagamento extends Relatorio {
    public RelatorioPagamento(Exporter exporter) { super(exporter); }
    protected String montarConteudo() {
        return "Relatorio de Pagamentos\nUsuario 7; R$ 3120.00\n";
    }
}
