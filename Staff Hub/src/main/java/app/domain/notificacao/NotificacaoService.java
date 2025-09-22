// 10) NotificacaoService (contexto)
package domain.notificacao;

import infra.AuditLogger;

public class NotificacaoService {
    private final NotificacaoStrategy estrategia;
    public NotificacaoService(NotificacaoStrategy estrategia) {
        this.estrategia = estrategia;
    }
    public void notificar(int idUsuario, String mensagem) {
        estrategia.enviar(idUsuario, mensagem);
        AuditLogger.getInstance().log("sistema@local", "NOTIFICAR", "Notificacao",
                "usuario="+idUsuario+" canal="+estrategia.canal());
    }
}
