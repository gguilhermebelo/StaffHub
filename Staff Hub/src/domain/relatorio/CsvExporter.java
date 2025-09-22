package domain.relatorio;
public class CsvExporter implements Exporter {
    public byte[] exportar(String conteudo) {
        System.out.println("Exportando CSV (simulado)...");
        return conteudo.getBytes();
    }
    public String tipo(){ return "CSV"; }
}
