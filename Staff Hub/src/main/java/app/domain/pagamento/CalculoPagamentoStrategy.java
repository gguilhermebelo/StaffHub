// 3) CalculoPagamentoStrategy
package domain.pagamento;

public interface CalculoPagamentoStrategy {
    double calcularLiquido(double salarioBase, int idUsuario);
    String getTipo();
}
