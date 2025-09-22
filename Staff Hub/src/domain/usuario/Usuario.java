package domain.usuario;

public class Usuario {
    public Integer id;
    public String nome;
    public String cpf;
    public String email;

    public Usuario() {}
    public Usuario(Integer id, String nome, String cpf, String email) {
        this.id = id; this.nome = nome; this.cpf = cpf; this.email = email;
    }

    @Override public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', cpf='" + cpf + "', email='" + email + "'}";
    }
}
