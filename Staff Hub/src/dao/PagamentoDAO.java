// 20) PagamentoDAO
package dao;

import infra.DatabaseConnection;
import java.sql.*;

public class PagamentoDAO {
    private final Connection conn = DatabaseConnection.getInstance().getConnection();

    public long salvar(int idUsuario, double valorLiquido, String tipo) {
        String sql = "INSERT INTO pagamento (id_usuario, data_pagamento, valor_liquido, tipo_calculo) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUsuario);
            ps.setDouble(2, valorLiquido);
            ps.setString(3, tipo);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { return rs.next() ? rs.getLong(1) : -1L; }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
