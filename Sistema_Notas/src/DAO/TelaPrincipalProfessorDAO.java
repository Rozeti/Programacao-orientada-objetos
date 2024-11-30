package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TelaPrincipalProfessorDAO {

    public String getNomeCompletoPorMatricula(int matricula) {
        String nomeCompleto = "Professor"; // Valor padrão caso o nome não seja encontrado
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter conexão com o banco de dados
            conn = ConexaoDAO.getConexao();

            // Consulta para buscar o nome completo do professor
            String sql = "SELECT nome FROM professor WHERE matricula_professor = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            // Executar a consulta
            rs = stmt.executeQuery();
            if (rs.next()) {
                // Recupera o nome do professor
                nomeCompleto = rs.getString("nome");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o nome completo do professor: " + e.getMessage());
        } finally {
            // Fechar recursos do banco de dados
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return nomeCompleto;
    }

    public List<String[]> getAlunosNotasPorMatricula(int matriculaProfessor) {
        List<String[]> alunosNotas = new java.util.ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter conexão com o banco de dados
            conn = ConexaoDAO.getConexao();

            // Consulta SQL para buscar alunos e suas notas associados ao professor
            String sql = """
                SELECT 
                    a.nome AS aluno,
                    d.denominacao AS disciplina,
                    n.nota_prova_1,
                    n.nota_prova_2,
                    n.nota_prova_3,
                    n.nota_prova_4,
                    ROUND((n.nota_prova_1 + n.nota_prova_2 + n.nota_prova_3 + n.nota_prova_4) / 4, 2) AS media_final
                FROM 
                    nota n
                JOIN 
                    professor p ON n.professor_cod_professor = p.cod_professor
                JOIN 
                    aluno a ON n.aluno_cod_aluno = a.cod_aluno
                JOIN 
                    disciplina d ON n.disciplina_codigo = d.codigo
                WHERE 
                    p.matricula_professor = ?
                ORDER BY 
                    a.nome, d.denominacao
            """;

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matriculaProfessor);

            // Executar a consulta
            rs = stmt.executeQuery();
            while (rs.next()) {
                String aluno = rs.getString("aluno");
                String disciplina = rs.getString("disciplina");
                String nota1 = rs.getString("nota_prova_1");
                String nota2 = rs.getString("nota_prova_2");
                String nota3 = rs.getString("nota_prova_3");
                String nota4 = rs.getString("nota_prova_4");
                String mediaFinal = rs.getString("media_final");

                // Adiciona os dados no formato desejado
                alunosNotas.add(new String[]{aluno, disciplina, nota1, nota2, nota3, nota4, mediaFinal});
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar alunos e notas: " + e.getMessage());
        } finally {
            // Fechar recursos do banco de dados
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return alunosNotas;
    }

    public void atualizarNota(String aluno, String disciplina, int numeroProva, String novoValor, int matriculaProfessor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Obter conexão com o banco de dados
            conn = ConexaoDAO.getConexao();

            // Determina qual coluna de nota atualizar com base no número da prova
            String sql = "";
            switch (numeroProva) {
                case 1:
                    sql = "UPDATE nota SET nota_prova_1 = ? WHERE aluno_cod_aluno = (SELECT cod_aluno FROM aluno WHERE nome = ?) AND disciplina_codigo = (SELECT codigo FROM disciplina WHERE denominacao = ?) AND professor_cod_professor = (SELECT cod_professor FROM professor WHERE matricula_professor = ?)";
                    break;
                case 2:
                    sql = "UPDATE nota SET nota_prova_2 = ? WHERE aluno_cod_aluno = (SELECT cod_aluno FROM aluno WHERE nome = ?) AND disciplina_codigo = (SELECT codigo FROM disciplina WHERE denominacao = ?) AND professor_cod_professor = (SELECT cod_professor FROM professor WHERE matricula_professor = ?)";
                    break;
                case 3:
                    sql = "UPDATE nota SET nota_prova_3 = ? WHERE aluno_cod_aluno = (SELECT cod_aluno FROM aluno WHERE nome = ?) AND disciplina_codigo = (SELECT codigo FROM disciplina WHERE denominacao = ?) AND professor_cod_professor = (SELECT cod_professor FROM professor WHERE matricula_professor = ?)";
                    break;
                case 4:
                    sql = "UPDATE nota SET nota_prova_4 = ? WHERE aluno_cod_aluno = (SELECT cod_aluno FROM aluno WHERE nome = ?) AND disciplina_codigo = (SELECT codigo FROM disciplina WHERE denominacao = ?) AND professor_cod_professor = (SELECT cod_professor FROM professor WHERE matricula_professor = ?)";
                    break;
                default:
                    System.out.println("Número de prova inválido");
                    return; // Caso o número da prova não seja válido
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, novoValor);
            stmt.setString(2, aluno);
            stmt.setString(3, disciplina);
            stmt.setInt(4, matriculaProfessor);

            // Executar a atualização
            stmt.executeUpdate();
            System.out.println("Nota atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar nota: " + e.getMessage());
        } finally {
            // Fechar recursos do banco de dados
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
