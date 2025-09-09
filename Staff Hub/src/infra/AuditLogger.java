// 2) AuditLogger
package infra;

import java.time.LocalDateTime;

public class AuditLogger {
    private static AuditLogger instance;
    private AuditLogger() {}
    public static AuditLogger getInstance() {
        if (instance == null) instance = new AuditLogger();
        return instance;
    }
    public void log(String user, String acao, String alvo, String detalhe) {
        System.out.printf("[%s] user=%s action=%s target=%s detail=%s%n",
                LocalDateTime.now(), user, acao, alvo, detalhe);
    }
}
