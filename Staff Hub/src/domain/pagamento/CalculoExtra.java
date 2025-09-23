package domain.pagamento;
public class CalculoExtra implements CalculoPagamentoStrategy {
    public double calcularLiquido(double salarioBase, int idUsuario) {
        return salarioBase + 100.0;
    }
    public String getTipo(){ return "Extra"; }
}
