package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastroAlunoDAO {

    public boolean cadastrarAluno(String nome, int matricula, String endereco, String telefone, String filiacao,
                                  String dataNascimento, int codigoCurso, String senha) {
        String sql = "INSERT INTO aluno (nome, matricula_aluno, endereco, telefone, filiacao, data_nascimento, curso_codigo, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, matricula);
            stmt.setString(3, endereco);
            stmt.setString(4, telefone);
            stmt.setString(5, filiacao);
            stmt.setString(6, dataNascimento);
            stmt.setInt(7, codigoCurso);
            stmt.setString(8, senha);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
