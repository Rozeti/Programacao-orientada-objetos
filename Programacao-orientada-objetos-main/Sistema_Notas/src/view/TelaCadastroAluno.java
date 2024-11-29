package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import DAO.TelaCadastroAlunoDAO;
import DAO.ConexaoDAO;

public class TelaCadastroAluno implements ActionListener {

    JFrame frame;
    JButton cadastrar;
    JButton voltar;

    // Campos do formulário
    JTextField caixaNome, caixaMatricula, caixaEndereco, caixaNum, caixaPais, caixaData;
    JPasswordField caixaSenha;
    JComboBox<String> series;

    // Construtor da tela de cadastro
    TelaCadastroAluno() {
        frame = new JFrame();

        ImageIcon iconeWindow = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");

        // Configuração do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Prime Scholars Academy");
        frame.setSize(350, 750);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0x194A70));
        frame.setIconImage(iconeWindow.getImage());

        // Cabeçalho
        JLabel labelHeader = new JLabel();
        ImageIcon iconeHeaderOriginal = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");
        Image iconeRedimensionado = iconeHeaderOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon iconeHeader = new ImageIcon(iconeRedimensionado);

        labelHeader.setBounds(0, 0, 350, 80);
        labelHeader.setIcon(iconeHeader);
        labelHeader.setText("Prime Scholars Academy");
        labelHeader.setFont(new Font("Comic Sans", Font.BOLD, 13));
        labelHeader.setForeground(new Color(0xBC994E));
        labelHeader.setBackground(new Color(0x194A70));
        labelHeader.setOpaque(true);

        // Botão de Voltar
        voltar = new JButton("Voltar");
        voltar.setBounds(260, 25, 70, 30);
        voltar.setFocusable(false);
        voltar.setFont(new Font("Comic Sans", Font.PLAIN, 12));
        voltar.setBackground(new Color(0x0A273D));
        voltar.setForeground(Color.white);
        voltar.addActionListener(this);

        // Título
        JLabel labelPerfil = new JLabel("Cadastro do Aluno");
        labelPerfil.setBounds(94, 80, 162, 20);
        labelPerfil.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelPerfil.setForeground(new Color(0xBC994E));
        labelPerfil.setBackground(new Color(0x194A70));
        labelPerfil.setOpaque(true);

        JLabel labelLinha = new JLabel();
        labelLinha.setBounds(10, 105, 310, 3);
        labelLinha.setForeground(new Color(0xBC994E));
        labelLinha.setBackground(new Color(0x0A273D));
        labelLinha.setOpaque(true);

        // Campos do formulário
        caixaNome = criarCampoTexto("Nome do Aluno:", 120, 145);
        caixaMatricula = criarCampoTexto("Matricula do Aluno:", 185, 210);
        caixaEndereco = criarCampoTexto("Endereço do Aluno:", 250, 275);
        caixaNum = criarCampoTexto("Número de Contato:", 315, 340);
        caixaPais = criarCampoTexto("Nome do Responsável:", 380, 405);

        // Série - Carregar cursos do banco de dados
        JLabel tituloComboBox = new JLabel("Selecione a Série:");
        tituloComboBox.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloComboBox.setForeground(new Color(0xBC994E));
        tituloComboBox.setBounds(20, 445, 180, 30);
        frame.add(tituloComboBox);

        series = new JComboBox<>();
        series.setBounds(10, 470, 310, 30);

        // Carregar cursos do banco
        carregarCursos();

        // Data de Nascimento
        caixaData = criarCampoTexto("Data de Nascimento:", 510, 535);

        // Senha
        JLabel tituloSenha = new JLabel("Senha:");
        tituloSenha.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloSenha.setForeground(new Color(0xBC994E));
        tituloSenha.setBounds(20, 575, 200, 30);
        frame.add(tituloSenha);

        caixaSenha = new JPasswordField();
        caixaSenha.setBounds(10, 600, 310, 30);
        caixaSenha.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        // Botão de Cadastrar
        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(100, 650, 140, 40);
        cadastrar.setFocusable(false);
        cadastrar.setFont(new Font("Comic Sans", Font.BOLD, 20));
        cadastrar.setBackground(new Color(0x216700));
        cadastrar.setForeground(Color.white);
        cadastrar.addActionListener(this);

        // Adicionando ao frame
        frame.add(voltar);
        frame.add(labelHeader);
        frame.add(labelPerfil);
        frame.add(labelLinha);
        frame.add(series);
        frame.add(cadastrar);
        frame.add(caixaSenha);

        frame.setVisible(true);
    }

    private JTextField criarCampoTexto(String titulo, int yLabel, int yField) {
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Comic Sans", Font.BOLD, 17));
        label.setForeground(new Color(0xBC994E));
        label.setBounds(20, yLabel, 180, 30);
        frame.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(10, yField, 310, 30);
        campo.setFont(new Font("Comic Sans", Font.PLAIN, 17));
        frame.add(campo);

        return campo;
    }

    private void carregarCursos() {
        try (Connection conn = ConexaoDAO.getConexao();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT codigo, nome FROM curso"; // Vamos pegar o código e nome do curso
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String curso = rs.getString("nome");
                series.addItem(curso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
            String nome = caixaNome.getText();
            String matriculaText = caixaMatricula.getText();
            String endereco = caixaEndereco.getText();
            String telefone = caixaNum.getText();
            String filiacao = caixaPais.getText();
            String dataNascimento = caixaData.getText();
            String cursoNome = (String) series.getSelectedItem();
            String senha = new String(caixaSenha.getPassword());

            if (nome.isEmpty() || matriculaText.isEmpty() || endereco.isEmpty() || telefone.isEmpty() ||
                filiacao.isEmpty() || dataNascimento.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int matricula = Integer.parseInt(matriculaText);
                int codigoCurso = getCodigoCurso(cursoNome);

                if (codigoCurso == -1) {
                    JOptionPane.showMessageDialog(frame, "Curso inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TelaCadastroAlunoDAO dao = new TelaCadastroAlunoDAO();
                if (dao.cadastrarAluno(nome, matricula, endereco, telefone, filiacao, dataNascimento, codigoCurso, senha)) {
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new TelaLoginAluno(); // Redireciona para a tela de login
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Matrícula deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == voltar) {
            frame.dispose();
        }
    }

    // Método para obter o código do curso baseado no nome
    private int getCodigoCurso(String nomeCurso) {
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement("SELECT codigo FROM curso WHERE nome = ?")) {

            stmt.setString(1, nomeCurso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("codigo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 caso não encontre o curso
    }
}
