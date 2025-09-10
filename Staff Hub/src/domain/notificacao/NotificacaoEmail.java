// 8) NotificacaoEmail
package domain.notificacao;

public class NotificacaoEmail implements NotificacaoStrategy {
    public void enviar(int idUsuario, String msg) {
        System.out.println("EMAIL -> usuario="+idUsuario+": "+msg);
    }
    public String canal() { return "EMAIL"; }
}
