package infra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditLogger {
    private static AuditLogger instance;
    private final String file = "data/audit.log";

    private AuditLogger(){}

    public static AuditLogger getInstance() {
        if (instance == null) instance = new AuditLogger();
        return instance;
    }

    public synchronized void log(String actor, String action, String target, String detail) {
        String linha = String.format("[%s] actor=%s action=%s target=%s detail=%s",
                LocalDateTime.now(), actor, action, target, detail);

        System.out.println(linha);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever log", e);
        }
    }
}
