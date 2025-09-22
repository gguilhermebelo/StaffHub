package domain.notificacao;
public class NotificacaoEmail implements NotificacaoStrategy {
    public void enviar(int idUsuario, String mensagem) {
        System.out.println("[EMAIL] user="+idUsuario+" :: "+mensagem);
    }
    public String canal(){ return "EMAIL"; }
}
