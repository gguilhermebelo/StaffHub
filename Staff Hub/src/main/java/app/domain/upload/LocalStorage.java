// 14) LocalStorage (Concrete Implementor)
package domain.upload;

public class LocalStorage implements Storage {
    public String salvar(String arquivoLocal) {
        // mock: mover para /uploads/atestados/
        return "/uploads/atestados/" + System.nanoTime() + "-" + arquivoLocal;
    }
}
