// 6) PagamentoService (contexto)
package domain.pagamento;

import infra.AuditLogger;

public class PagamentoService {
    private final CalculoPagamentoStrategy estrategia;
    public PagamentoService(CalculoPagamentoStrategy estrategia) {
        this.estrategia = estrategia;
    }
    public double processarPagamento(int idUsuario, double salarioBase) {
        double liquido = estrategia.calcularLiquido(salarioBase, idUsuario);
        AuditLogger.getInstance().log("sistema@local", "PAGAR", "Pagamento",
                "usuario="+idUsuario+" tipo="+estrategia.getTipo()+" liquido="+liquido);
        return liquido; // persistência real fica no DAO
    }
}
