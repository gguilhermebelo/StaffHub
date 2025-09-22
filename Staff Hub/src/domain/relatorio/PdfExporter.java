package domain.relatorio;
public class PdfExporter implements Exporter {
    public byte[] exportar(String conteudo) {
        System.out.println("Exportando PDF (simulado)...");
        return conteudo.getBytes();
    }
    public String tipo(){ return "PDF"; }
}
