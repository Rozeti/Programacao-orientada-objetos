package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaCadastroProfessorDAO {

    public void cadastrarProfessor(String nome, int matricula, String formacao, String endereco, String numeroContato,
                                    String disciplina, String departamento, String senha) {
        int departamentoCodigo = getDepartamentoCodigo(departamento);

        // Caso o departamento não exista, insira-o
        if (departamentoCodigo == -1) {
            departamentoCodigo = inserirDepartamento(departamento);
        }

        String sql = "INSERT INTO professor (nome, matricula_professor, formacao, endereco, telefone, disciplina_nome, departamento_codigo, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, matricula);
            stmt.setString(3, formacao);
            stmt.setString(4, endereco);
            stmt.setString(5, numeroContato);
            stmt.setString(6, disciplina);
            stmt.setInt(7, departamentoCodigo); // Usar o código do departamento
            stmt.setString(8, senha);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private int getDepartamentoCodigo(String departamentoNome) {
        String sql = "SELECT codigo FROM departamento WHERE denominacao = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departamentoNome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Departamento não encontrado
    }

    private int inserirDepartamento(String departamentoNome) {
        String sql = "INSERT INTO departamento (denominacao) VALUES (?)";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, departamentoNome);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna o código do novo departamento
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Erro ao inserir departamento
    }
}
