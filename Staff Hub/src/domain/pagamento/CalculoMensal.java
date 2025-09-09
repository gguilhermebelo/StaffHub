// 4) CalculoMensal
package domain.pagamento;

public class CalculoMensal implements CalculoPagamentoStrategy {
    public double calcularLiquido(double salarioBase, int idUsuario) {
        double proventos = salarioBase + 200.0; // vale/bônus simples
        double descontos = salarioBase * 0.08;  // INSS simplificado
        return proventos - descontos;
    }
    public String getTipo() { return "Mensal"; }
}
