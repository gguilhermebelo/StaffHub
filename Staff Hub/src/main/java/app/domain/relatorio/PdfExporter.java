// 18) PdfExporter (Concrete Implementor)
package domain.relatorio;

public class PdfExporter implements Exporter {
    public byte[] exportar(String conteudo) {
        // mock: converter string para PDF (usar lib real depois)
        return conteudo.getBytes();
    }
}
