package dao;

import domain.usuario.Usuario;
import infra.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection conn = DatabaseConnection.getInstance().getConnection();

    public List<Usuario> listarTodos() {
        String sql = "SELECT id_usuario, nome, cpf, email FROM usuario ORDER BY id_usuario DESC";
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return lista;
    }

        private void salvar() {
        try {
            String nome  = txtNome.getText().trim();
            String cpf   = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha Nome, CPF e E-mail.");
                return;
            }

            String idStr = txtId.getText().trim();
            boolean criando = idStr.isBlank();

            // *** senha obrigatória apenas na criação ***
            if (criando && senha.isBlank()) {
                JOptionPane.showMessageDialog(this, "Defina uma senha para novo usuário.");
                return;
            }

            String senhaHash = senha.isBlank() ? null : HashUtil.sha256(senha);

            Usuario u = new Usuario(criando ? null : Integer.parseInt(idStr), nome, cpf, email);
            u.setSenha(senhaHash); // no update pode ser null para manter a antiga

            usuarioDAO.salvar(u);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            carregarTabela();
            limparForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }


    public Usuario criar(Usuario u) {
        String sql = "INSERT INTO usuario (nome, cpf, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCpf());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setId(rs.getInt(1));
            }
            return u;
        } catch (SQLException e) {
            if (e.getMessage()!=null && e.getMessage().contains("Duplicate")) {
                throw new RuntimeException("CPF ou e-mail já cadastrado.");
            }
            throw new RuntimeException(e);
        }
    }

    public Usuario atualizar(Usuario u) {
        String sql = "UPDATE usuario SET nome=?, cpf=?, email=? WHERE id_usuario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCpf());
            ps.setString(3, u.getEmail());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
            return u;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
