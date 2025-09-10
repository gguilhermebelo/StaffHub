package domain.usuario;

public class Usuario {
    private Integer id; // id_usuario
    private String nome;
    private String cpf;
    private String email;

    public Usuario() {}
    public Usuario(Integer id, String nome, String cpf, String email) {
        this.id = id; this.nome = nome; this.cpf = cpf; this.email = email;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
