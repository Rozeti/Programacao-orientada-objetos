package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DAO.TelaPrincipalProfessorDAO;

public class TelaPrincipalProfessor implements ActionListener {
    JFrame frame;
    JButton btnSair, btnAtualizarNota;
    TelaPrincipalProfessorDAO telaPrincipalProfessorDAO;
    int matriculaProfessor;
    JTable tabela;
    DefaultTableModel modeloTabela;
    JTextField[] camposNotas; // Para armazenar os campos de notas
    String alunoSelecionado, disciplinaSelecionada;
    int linhaSelecionada;

    public TelaPrincipalProfessor(int matricula) {
        this.matriculaProfessor = matricula;
        telaPrincipalProfessorDAO = new TelaPrincipalProfessorDAO();

        frame = new JFrame();
        ImageIcon iconeWindow = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");

        // Configuração do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Prime Scholars Academy");
        frame.setSize(450, 750);
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
        labelHeader.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelHeader.setForeground(new Color(0xBC994E));
        labelHeader.setBackground(new Color(0x194A70));
        labelHeader.setOpaque(true);

        // Botão de Sair
        btnSair = new JButton("Sair");
        btnSair.setBounds(350, 25, 70, 30);
        btnSair.setFocusable(false);
        btnSair.setFont(new Font("Comic Sans", Font.PLAIN, 12));
        btnSair.setBackground(new Color(0x0A273D));
        btnSair.setForeground(Color.white);
        btnSair.addActionListener(this);

        // Saudação
        String nomeCompleto = telaPrincipalProfessorDAO.getNomeCompletoPorMatricula(matricula);
        JLabel lblSaudacao = new JLabel("Olá, " + nomeCompleto);
        lblSaudacao.setBounds(120, 80, 220, 20);
        lblSaudacao.setFont(new Font("Comic Sans", Font.BOLD, 20));
        lblSaudacao.setForeground(new Color(0xBC994E));

        // Tabela para exibir alunos e notas
        String[] colunas = {"Aluno", "Disciplina", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Média"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        tabela.setRowHeight(25);

        // Ajuste das larguras das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(190); // Coluna "Aluno"
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100); // Coluna "Disciplina"
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);  // Coluna "Nota 1"
        tabela.getColumnModel().getColumn(3).setPreferredWidth(50);  // Coluna "Nota 2"
        tabela.getColumnModel().getColumn(4).setPreferredWidth(50);  // Coluna "Nota 3"
        tabela.getColumnModel().getColumn(5).setPreferredWidth(50);  // Coluna "Nota 4"
        tabela.getColumnModel().getColumn(6).setPreferredWidth(60);  // Coluna "Média"

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 120, 410, 500);
        frame.add(scrollPane);

        // Preenche a tabela com os dados dos alunos e suas notas
        List<String[]> alunosNotas = telaPrincipalProfessorDAO.getAlunosNotasPorMatricula(matricula);
        for (String[] alunoNota : alunosNotas) {
            modeloTabela.addRow(alunoNota);
        }

        // Campos de notas
        camposNotas = new JTextField[4]; // Para as 4 provas
        JPanel painelNotas = new JPanel();
        painelNotas.setBounds(10, 630, 410, 40);
        painelNotas.setBackground(new Color(0x194A70));

        for (int i = 0; i < 4; i++) {
            JTextField campoNota = new JTextField(5);
            campoNota.setFont(new Font("Comic Sans", Font.PLAIN, 14));
            campoNota.setForeground(Color.BLACK);
            campoNota.setBackground(new Color(0xBC994E));
            campoNota.setHorizontalAlignment(JTextField.CENTER);
            painelNotas.add(campoNota);
            camposNotas[i] = campoNota;
        }

        // Botão de Atualizar
        btnAtualizarNota = new JButton("Atualizar");
        btnAtualizarNota.setBounds(150, 665, 130, 40);
        btnAtualizarNota.setFont(new Font("Comic Sans", Font.BOLD, 14));
        btnAtualizarNota.setBackground(new Color(0xBC994E));
        btnAtualizarNota.setForeground(Color.white);
        btnAtualizarNota.addActionListener(this);

        // Adicionar os componentes ao frame
        frame.add(labelHeader);
        frame.add(btnSair);
        frame.add(lblSaudacao);
        frame.add(painelNotas);
        frame.add(btnAtualizarNota);

        // Exibe o frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            frame.dispose();
        } else if (e.getSource() == btnAtualizarNota) {
            // Obter os dados da linha selecionada
            int row = tabela.getSelectedRow();
            if (row != -1) {
                alunoSelecionado = (String) modeloTabela.getValueAt(row, 0);
                disciplinaSelecionada = (String) modeloTabela.getValueAt(row, 1);

                // Obter as notas dos campos de texto
                for (int i = 0; i < 4; i++) {
                    String novaNota = camposNotas[i].getText();
                    if (!novaNota.isEmpty()) {
                        // Atualiza a nota correspondente
                        int numeroProva = i + 1; // Prova 1, 2, 3 ou 4
                        telaPrincipalProfessorDAO.atualizarNota(alunoSelecionado, disciplinaSelecionada, numeroProva, novaNota, matriculaProfessor);
                        modeloTabela.setValueAt(novaNota, row, i + 2); // Atualiza a tabela (coluna 2 a 5)
                    }
                }
            }
        }
    }
}
