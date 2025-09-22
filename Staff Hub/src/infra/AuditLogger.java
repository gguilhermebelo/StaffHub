package infra;

import java.time.LocalDateTime;

public class AuditLogger {
    private static AuditLogger instance;
    private AuditLogger(){}

    public static AuditLogger getInstance() {
        if (instance == null) instance = new AuditLogger();
        return instance;
    }

    public void log(String actor, String action, String target, String detail) {
        System.out.printf("[%s] actor=%s action=%s target=%s detail=%s%n",
                LocalDateTime.now(), actor, action, target, detail);
    }
}
