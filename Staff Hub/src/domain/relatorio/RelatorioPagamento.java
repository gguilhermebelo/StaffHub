// 17) RelatorioPagamento (Refined Abstraction)
package domain.relatorio;

public class RelatorioPagamento extends Relatorio {
    public RelatorioPagamento(Exporter exporter) { super(exporter); }
    protected String montarConteudo() {
        // mock: normalmente viria do DAO/consulta
        return "Relatorio de Pagamentos\n- Usuario 7: R$ 3.120,00\n";
    }
}
