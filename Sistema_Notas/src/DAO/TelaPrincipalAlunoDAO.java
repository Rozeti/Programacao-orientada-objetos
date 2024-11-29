package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipalAlunoDAO {
    // Conexão com o banco de dados
    private Connection connection;

    public TelaPrincipalAlunoDAO() {
        try {
            this.connection = ConexaoDAO.getConexao(); // Usando ConexaoDAO
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter conexão com o banco de dados.");
        }
    }

    // Método para obter o primeiro nome do aluno com base na matrícula
    public String getPrimeiroNomePorMatricula(int matricula) {
        String primeiroNome = "";
        try {
            String sql = "SELECT nome FROM aluno WHERE matricula_aluno = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nomeCompleto = rs.getString("nome");
                primeiroNome = nomeCompleto.split(" ")[0]; // Pega apenas o primeiro nome
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primeiroNome;
    }

    // Método para obter as matérias e notas com base na matrícula
    public List<String[]> getMateriasNotasPorMatricula(int matricula) {
        List<String[]> materiasNotas = new ArrayList<>();
        try {
            // Consulta ajustada para refletir a estrutura correta
            String sql = "SELECT d.denominacao AS materia, " +
                         "n.nota_prova_1, n.nota_prova_2, n.nota_prova_3, n.nota_prova_4 " +
                         "FROM matricula_disciplina md " +
                         "JOIN disciplina d ON md.disciplina_codigo = d.codigo " +
                         "JOIN nota n ON md.aluno_cod_aluno = n.aluno_cod_aluno " +
                         "AND md.disciplina_codigo = n.disciplina_codigo " +
                         "WHERE md.aluno_cod_aluno = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();

            // Percorrendo o resultado e montando a lista de matérias e notas
            while (rs.next()) {
                String[] materiaNota = new String[5];
                materiaNota[0] = rs.getString("materia"); // Nome da matéria
                materiaNota[1] = String.valueOf(rs.getDouble("nota_prova_1")); // Nota 1
                materiaNota[2] = String.valueOf(rs.getDouble("nota_prova_2")); // Nota 2
                materiaNota[3] = String.valueOf(rs.getDouble("nota_prova_3")); // Nota 3
                materiaNota[4] = String.valueOf(rs.getDouble("nota_prova_4")); // Nota 4

                materiasNotas.add(materiaNota);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiasNotas;
    }
}
