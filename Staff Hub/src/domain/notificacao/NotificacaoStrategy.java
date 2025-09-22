package domain.notificacao;
public interface NotificacaoStrategy {
    void enviar(int idUsuario, String mensagem);
    String canal();
}
