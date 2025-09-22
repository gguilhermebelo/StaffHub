package domain.relatorio;
public class RelatorioFerias extends Relatorio {
    public RelatorioFerias(Exporter exporter) { super(exporter); }
    protected String montarConteudo() {
        return "Relatorio de Ferias\nUsuario 7; 10 dias aprovados\n";
    }
}
