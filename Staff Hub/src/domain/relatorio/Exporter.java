package domain.relatorio;
public interface Exporter {
    byte[] exportar(String conteudo);
    String tipo();
}
