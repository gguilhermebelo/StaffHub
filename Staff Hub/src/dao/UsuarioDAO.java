// 19) UsuarioDAO
package dao;

import infra.DatabaseConnection;
import java.sql.*;

public class UsuarioDAO {
    private final Connection conn = DatabaseConnection.getInstance().getConnection();

    public void criar(String nome, String cpf, String email) {
        String sql = "INSERT INTO usuario (nome, cpf, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    // read/update/delete...
}
