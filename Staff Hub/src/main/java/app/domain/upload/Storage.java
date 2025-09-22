// 12) Storage (Implementor)
package domain.upload;

public interface Storage {
    String salvar(String arquivoLocal); // retorna URL/caminho
}
