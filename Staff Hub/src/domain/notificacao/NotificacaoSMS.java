package domain.notificacao;
public class NotificacaoSMS implements NotificacaoStrategy {
    public void enviar(int idUsuario, String mensagem) {
        System.out.println("[SMS] user="+idUsuario+" :: "+mensagem);
    }
    public String canal(){ return "SMS"; }
}
