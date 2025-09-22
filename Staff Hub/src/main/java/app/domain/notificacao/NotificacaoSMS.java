// 9) NotificacaoSMS
package domain.notificacao;

public class NotificacaoSMS implements NotificacaoStrategy {
    public void enviar(int idUsuario, String msg) {
        System.out.println("SMS -> usuario="+idUsuario+": "+msg);
    }
    public String canal() { return "SMS"; }
}
