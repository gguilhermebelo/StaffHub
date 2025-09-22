package domain.pagamento;

import infra.AuditLogger;

public class PagamentoService {
    private final CalculoPagamentoStrategy estrategia;
    public PagamentoService(CalculoPagamentoStrategy estrategia) { this.estrategia = estrategia; }

    public double processar(int idUsuario, double salarioBase) {
        double liquido = estrategia.calcularLiquido(salarioBase, idUsuario);
        System.out.printf("Pagamento %s -> user %d = R$ %.2f%n",
                estrategia.getTipo(), idUsuario, liquido);
        AuditLogger.getInstance().log("sistema", "PAGAR", "Pagamento",
                "tipo="+estrategia.getTipo()+" user="+idUsuario+" liquido="+liquido);
        return liquido;
    }
}
