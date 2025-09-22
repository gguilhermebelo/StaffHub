package domain.relatorio;
public abstract class Relatorio {
    protected final Exporter exporter;
    protected Relatorio(Exporter exporter){ this.exporter = exporter; }
    public final byte[] gerar(){
        String conteudo = montarConteudo();
        System.out.println("Gerando relatório -> " + exporter.tipo());
        return exporter.exportar(conteudo);
    }
    protected abstract String montarConteudo();
}
