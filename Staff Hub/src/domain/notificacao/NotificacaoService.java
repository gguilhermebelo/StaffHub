package domain.notificacao;

import infra.AuditLogger;

public class NotificacaoService {
    private final NotificacaoStrategy strategy;
    public NotificacaoService(NotificacaoStrategy strategy) { this.strategy = strategy; }

    public void notificar(int idUsuario, String mensagem){
        strategy.enviar(idUsuario, mensagem);
        AuditLogger.getInstance().log("sistema", "NOTIFICAR", "Notificacao",
                "canal="+strategy.canal()+" user="+idUsuario);
    }
}
