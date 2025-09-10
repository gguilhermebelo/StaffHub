// 15) Relatorio (Abstraction)
package domain.relatorio;

public abstract class Relatorio {
    protected final Exporter exporter; // Implementor
    public Relatorio(Exporter exporter) { this.exporter = exporter; }
    public final byte[] gerar() {
        String conteudo = montarConteudo();
        return exporter.exportar(conteudo);
    }
    protected abstract String montarConteudo();
}
