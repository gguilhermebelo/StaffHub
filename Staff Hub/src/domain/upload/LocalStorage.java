package domain.upload;

import infra.ConfigManager;
import java.nio.file.*;

public class LocalStorage implements Storage {
    public String salvar(String arquivoLocal) {
        try {
            Path dir = Paths.get(ConfigManager.getInstance().get("storage.dir"), "local");
            if (!Files.exists(dir)) Files.createDirectories(dir);
            Path destino = dir.resolve(System.nanoTime() + "-" + arquivoLocal);
            // só simula a cópia
            Files.writeString(destino, "ARQUIVO_FAKE");
            System.out.println("Salvando local -> " + destino.toString());
            return destino.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
