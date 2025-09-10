package ui;

import dao.UsuarioDAO;
import domain.usuario.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioCRUDFrame extends JFrame {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTable tabela;
    private DefaultTableModel modelo;
    private JTextField txtId = new JTextField(6);
    private JTextField txtNome = new JTextField(22);
    private JTextField txtCpf = new JTextField(14);
    private JTextField txtEmail = new JTextField(22);

    private JButton btnNovo = new JButton("Novo");
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnRecarregar = new JButton("Recarregar");

    public UsuarioCRUDFrame() {
        setTitle("CRUD Usuário");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 450);
        setLocationRelativeTo(null);

        // tabela
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "E-mail"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
                int row = tabela.getSelectedRow();
                txtId.setText(String.valueOf(modelo.getValueAt(row, 0)));
                txtNome.setText(String.valueOf(modelo.getValueAt(row, 1)));
                txtCpf.setText(String.valueOf(modelo.getValueAt(row, 2)));
                txtEmail.setText(String.valueOf(modelo.getValueAt(row, 3)));
            }
        });

        // form
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.gridx=0; c.gridy=0; c.anchor=GridBagConstraints.LINE_END; form.add(new JLabel("ID:"), c);
        c.gridx=1; c.anchor=GridBagConstraints.LINE_START; txtId.setEditable(false); form.add(txtId, c);
        c.gridx=0; c.gridy=1; c.anchor=GridBagConstraints.LINE_END; form.add(new JLabel("Nome:"), c);
        c.gridx=1; c.anchor=GridBagConstraints.LINE_START; form.add(txtNome, c);
        c.gridx=0; c.gridy=2; c.anchor=GridBagConstraints.LINE_END; form.add(new JLabel("CPF:"), c);
        c.gridx=1; c.anchor=GridBagConstraints.LINE_START; form.add(txtCpf, c);
        c.gridx=0; c.gridy=3; c.anchor=GridBagConstraints.LINE_END; form.add(new JLabel("E-mail:"), c);
        c.gridx=1; c.anchor=GridBagConstraints.LINE_START; form.add(txtEmail, c);

        // botoes
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnNovo); botoes.add(btnSalvar); botoes.add(btnExcluir); botoes.add(btnRecarregar);

        // layout geral
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(tabela), form);
        split.setDividerLocation(220);
        add(split, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        // actions
        btnRecarregar.addActionListener(e -> carregarTabela());
        btnNovo.addActionListener(e -> limparForm());
        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getCpf(), u.getEmail()});
        }
        limparForm();
    }

    private void limparForm() {
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        tabela.clearSelection();
    }

    private void salvar() {
        try {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha Nome, CPF e E-mail.");
                return;
            }
            Usuario u = new Usuario(
                    txtId.getText().isBlank() ? null : Integer.parseInt(txtId.getText()),
                    nome, cpf, email
            );
            usuarioDAO.salvar(u);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void excluir() {
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário.");
            return;
        }
        int op = JOptionPane.showConfirmDialog(this, "Excluir usuário selecionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try {
                usuarioDAO.deletar(Integer.parseInt(txtId.getText()));
                JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuarioCRUDFrame().setVisible(true));
    }
}
