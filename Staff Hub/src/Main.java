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

        // SINGLETONS
        System.out.println("-- SINGLETONS --");
        System.out.println("Env           : " + ConfigManager.getInstance().get("env"));
        System.out.println("Users file    : " + ConfigManager.getInstance().get("users.file"));
        AuditLogger.getInstance().log("professor", "ABRIR", "Demo", "inicio");

        // CRUD TXT (UsuarioStore)
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

        // STRATEGY #1 (Pagamento)
        System.out.println("\n-- STRATEGY: Pagamento --");
        new PagamentoService(new CalculoMensal()).processar(7, 3000.0);
        new PagamentoService(new CalculoExtra()).processar(7, 3000.0);

        // STRATEGY #2 (Notificação)
        System.out.println("\n-- STRATEGY: Notificacao --");
        new NotificacaoService(new NotificacaoEmail()).notificar(7, "Seu holerite está disponível.");
        new NotificacaoService(new NotificacaoSMS()).notificar(7, "Suas férias foram aprovadas!");

        // BRIDGE #1 (Upload)
        System.out.println("\n-- BRIDGE: Upload --");
        Uploader up1 = new AtestadoUploader(new LocalStorage());
        up1.upload("atestado.pdf");
        Uploader up2 = new HoleriteUploader(new S3Storage());
        up2.upload("holerite.pdf");

        // BRIDGE #2 (Relatorio)
        System.out.println("\n-- BRIDGE: Relatorio --");
        Relatorio r1 = new RelatorioPagamento(new PdfExporter());
        r1.gerar();
        Relatorio r2 = new RelatorioFerias(new CsvExporter());
        r2.gerar();

        System.out.println("\n==== FIM ====");
    }

    private static void listar(List<Usuario> users){
        users.forEach(u -> System.out.println(" - " + u));
    }
}
