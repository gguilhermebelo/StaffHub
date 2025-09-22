package domain.pagamento;
public class CalculoMensal implements CalculoPagamentoStrategy {
    public double calcularLiquido(double salarioBase, int idUsuario) {
        double proventos = salarioBase + 200.0;
        double descontos = salarioBase * 0.08;
        return proventos - descontos;
    }
    public String getTipo(){ return "Mensal"; }
}
