package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAO.TelaPrincipalProfessorDAO;

public class TelaPrincipalProfessor implements ActionListener {
    JFrame frame;
    JButton btnSair;
    TelaPrincipalProfessorDAO telaPrincipalProfessorDAO;

    public TelaPrincipalProfessor(int matricula) {
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

        // Recupera o nome completo do professor com base na matrícula
        String nomeCompleto = telaPrincipalProfessorDAO.getNomeCompletoPorMatricula(matricula);

        // Título com saudação
        JLabel lblSaudacao = new JLabel("Olá, " + nomeCompleto);
        lblSaudacao.setBounds(120, 80, 220, 20);
        lblSaudacao.setFont(new Font("Comic Sans", Font.BOLD, 20));
        lblSaudacao.setForeground(new Color(0xBC994E));
        lblSaudacao.setBackground(new Color(0x194A70));
        lblSaudacao.setOpaque(true);

        // Linha divisória
        JLabel lblLinha = new JLabel();
        lblLinha.setBounds(10, 105, 415, 3);
        lblLinha.setBackground(new Color(0xBC994E));
        lblLinha.setOpaque(true);

        // Corpo da sessão
        JPanel painelConteudo = new JPanel();
        painelConteudo.setFont(new Font("Comic Sans", Font.PLAIN, 18));
        painelConteudo.setForeground(Color.black);
        painelConteudo.setBackground(new Color(0xA0A0A0));
        painelConteudo.setOpaque(true);
        painelConteudo.setLayout(null);

        // Tabela para exibir os alunos e as notas
        String[] colunas = {"Nome do Aluno", "Nota Prova 1", "Nota Prova 2", "Nota Prova 3", "Nota Prova 4"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        tabela.setRowHeight(25);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setFillsViewportHeight(true);

        // Configura as colunas para ajustar o tamanho
        for (int i = 0; i < colunas.length; i++) {
            TableColumn column = tabela.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(180);
            } else {
                column.setPreferredWidth(60);
            }
        }

        // Adiciona o JScrollPane da tabela ao painel de conteúdo
        JScrollPane scrollPaneTabela = new JScrollPane(tabela);
        scrollPaneTabela.setBounds(10, 10, 380, 500);

        // Adiciona o JScrollPane ao painel de conteúdo
        painelConteudo.add(scrollPaneTabela);

        // Adiciona o JScrollPane ao frame
        JScrollPane scrollPane = new JScrollPane(painelConteudo);
        scrollPane.setBounds(10, 120, 410, 600);
        frame.add(scrollPane);

        // Preenche a tabela com os dados dos alunos
        String[] alunos = telaPrincipalProfessorDAO.getNomesAlunosPorMatricula(matricula);
        for (String aluno : alunos) {
            modeloTabela.addRow(new Object[]{aluno, "", "", "", ""});
        }

        // Adicionar os componentes ao frame
        frame.add(labelHeader);
        frame.add(btnSair);
        frame.add(lblSaudacao);
        frame.add(lblLinha);

        // Exibe o frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            frame.dispose();
            new TelaLoginProfessor();
        }
    }
}
