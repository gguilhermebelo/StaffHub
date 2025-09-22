// 16) Exporter (Implementor)
package domain.relatorio;

public interface Exporter {
    byte[] exportar(String conteudo);
}
