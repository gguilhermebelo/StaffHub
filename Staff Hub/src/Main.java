import infra.ConfigManager;
import infra.AuditLogger;

import domain.usuario.Usuario;
import domain.usuario.UsuarioStore;

import domain.pagamento.*;
import domain.notificacao.*;

import domain.upload.*;
import domain.relatorio.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== DEMO Padrões + CRUD TXT ====\n");
        System.out.println("WD: " + System.getProperty("user.dir"));

        System.out.println("-- SINGLETONS --");
        System.out.println("Env        : " + ConfigManager.getInstance().get("env"));
        System.out.println("Users file : " + ConfigManager.getInstance().get("users.file"));
        AuditLogger.getInstance().log("professor", "ABRIR", "Demo", "inicio");

        UsuarioStore store = new UsuarioStore();
        System.out.println("\n-- CRUD TXT: listar --");
        listar(store.listar());

        System.out.println("\n-- CRUD TXT: criar --");
        Usuario novo = new Usuario(null, "Carla Dias", "111.222.333-44", "carla@example.com");
        store.salvar(novo);
        listar(store.listar());

        System.out.println("\n-- CRUD TXT: atualizar --");
        novo.nome = "Carla Dias (editado)";
        store.salvar(novo);
        listar(store.listar());

        System.out.println("\n-- CRUD TXT: deletar --");
        store.deletar(novo.id);
        listar(store.listar());

        System.out.println("\n-- STRATEGY: Pagamento --");
        new PagamentoService(new CalculoMensal()).processar(7, 3000.0);
        new PagamentoService(new CalculoExtra()).processar(7, 3000.0);

        System.out.println("\n-- STRATEGY: Notificacao --");
        new NotificacaoService(new NotificacaoEmail()).notificar(7, "Seu holerite está disponível.");
        new NotificacaoService(new NotificacaoSMS()).notificar(7, "Suas férias foram aprovadas!");

        System.out.println("\n-- BRIDGE: Upload --");
        new AtestadoUploader(new LocalStorage()).upload("atestado.pdf");
        new HoleriteUploader(new S3Storage()).upload("holerite.pdf");

        System.out.println("\n-- BRIDGE: Relatorio --");
        new RelatorioPagamento(new PdfExporter()).gerar();
        new RelatorioFerias(new CsvExporter()).gerar();

        System.out.println("\n==== FIM ====");
    }

    private static void listar(List<Usuario> users){
        users.forEach(u -> System.out.println(" - " + u));
    }
}
