package infra;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static ConfigManager instance;
    private final Map<String, String> cfg = new HashMap<>();

    private ConfigManager() {
        cfg.put("env", "DEV");
        cfg.put("storage.dir", "uploads");
        cfg.put("users.file", "data/usuarios.txt"); // <- caminho do TXT
    }

    public static ConfigManager getInstance() {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }

    public String get(String key){ return cfg.get(key); }
    public void set(String key, String val){ cfg.put(key, val); }
}
