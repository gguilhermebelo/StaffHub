package domain.usuario;

import infra.ConfigManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Armazena usuários em um TXT CSV-like (id;nome;cpf;email).
 * É só um helper de arquivo (não é DAO/banco).
 */
public class UsuarioStore {
    private final Path file;
    private final AtomicInteger seq = new AtomicInteger(0);

    public UsuarioStore() {
        String path = ConfigManager.getInstance().get("users.file");
        this.file = Paths.get(path);
        initFileIfNeeded();
        carregarMaiorId();
    }

    private void initFileIfNeeded() {
        try {
            Path dir = file.getParent();
            if (dir != null && !Files.exists(dir)) Files.createDirectories(dir);
            if (!Files.exists(file)) {
                Files.createFile(file);
                // sementes
                List<String> seed = List.of(
                        "1;Ana Souza;123.456.789-00;ana@example.com",
                        "2;Bruno Lima;987.654.321-00;bruno@example.com"
                );
                Files.write(file, seed, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private void carregarMaiorId() {
        int max = listar().stream().map(u -> u.id).max(Integer::compareTo).orElse(0);
        seq.set(max);
    }

    public List<Usuario> listar() {
        try {
            if (!Files.exists(file)) return new ArrayList<>();
            return Files.readAllLines(file, StandardCharsets.UTF_8)
                    .stream()
                    .filter(l -> !l.isBlank())
                    .map(this::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private Usuario parse(String line) {
        String[] p = line.split(";", -1);
        return new Usuario(Integer.parseInt(p[0]), p[1], p[2], p[3]);
    }

    private String format(Usuario u) {
        return u.id + ";" + u.nome + ";" + u.cpf + ";" + u.email;
    }

    public Usuario salvar(Usuario u) {
        if (u.id == null) return criar(u);
        return atualizar(u);
    }

    private Usuario criar(Usuario u) {
        u.id = seq.incrementAndGet();
        try (BufferedWriter bw = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            bw.write(format(u)); bw.newLine();
        } catch (IOException e) { throw new RuntimeException(e); }
        return u;
    }

    private Usuario atualizar(Usuario u) {
        List<Usuario> todos = listar();
        boolean found = false;
        for (int i = 0; i < todos.size(); i++) {
            if (Objects.equals(todos.get(i).id, u.id)) {
                todos.set(i, u);
                found = true; break;
            }
        }
        if (!found) throw new RuntimeException("Usuário não encontrado: id=" + u.id);
        try {
            List<String> linhas = todos.stream().map(this::format).collect(Collectors.toList());
            Files.write(file, linhas, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) { throw new RuntimeException(e); }
        return u;
    }

    public void deletar(int id) {
        List<Usuario> todos = listar();
        List<Usuario> rest = todos.stream().filter(u -> !Objects.equals(u.id, id)).collect(Collectors.toList());
        try {
            List<String> linhas = rest.stream().map(this::format).collect(Collectors.toList());
            Files.write(file, linhas, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) { throw new RuntimeException(e); }
    }
}
