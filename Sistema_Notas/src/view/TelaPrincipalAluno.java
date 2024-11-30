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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.TelaPrincipalAlunoDAO;

public class TelaPrincipalAluno implements ActionListener {
    JFrame frame;
    JButton btnSair;
    TelaPrincipalAlunoDAO telaPrincipalAlunoDAO;

    public TelaPrincipalAluno(int matricula) {
        telaPrincipalAlunoDAO = new TelaPrincipalAlunoDAO();

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
        String primeiroNome = telaPrincipalAlunoDAO.getPrimeiroNomePorMatricula(matricula);
        JLabel lblSaudacao = new JLabel("Olá, " + primeiroNome);
        lblSaudacao.setBounds(120, 80, 220, 20);
        lblSaudacao.setFont(new Font("Comic Sans", Font.BOLD, 20));
        lblSaudacao.setForeground(new Color(0xBC994E));

        // Tabela para exibir disciplinas, notas e média
        String[] colunas = {"Disciplina", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Média"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        tabela.setRowHeight(25);

        // Ajuste das larguras das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Coluna "Disciplina"
        tabela.getColumnModel().getColumn(1).setPreferredWidth(50);  // Coluna "Nota 1"
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);  // Coluna "Nota 2"
        tabela.getColumnModel().getColumn(3).setPreferredWidth(50);  // Coluna "Nota 3"
        tabela.getColumnModel().getColumn(4).setPreferredWidth(50);  // Coluna "Nota 4"
        tabela.getColumnModel().getColumn(5).setPreferredWidth(60);  // Coluna "Média"

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 120, 410, 600);
        frame.add(scrollPane);

        // Preenche a tabela com os dados das disciplinas e notas
        List<String[]> materiasNotas = telaPrincipalAlunoDAO.getMateriasNotasPorMatricula(matricula);
        for (String[] materiaNota : materiasNotas) {
            String disciplina = materiaNota[0];
            String nota1 = materiaNota[1].equals("null") ? "N/A" : materiaNota[1];
            String nota2 = materiaNota[2].equals("null") ? "N/A" : materiaNota[2];
            String nota3 = materiaNota[3].equals("null") ? "N/A" : materiaNota[3];
            String nota4 = materiaNota[4].equals("null") ? "N/A" : materiaNota[4];

            // Calcula a média das notas
            double media = 0;
            int notasValidas = 0;
            double[] notas = {Double.parseDouble(nota1.equals("N/A") ? "0" : nota1),
                              Double.parseDouble(nota2.equals("N/A") ? "0" : nota2),
                              Double.parseDouble(nota3.equals("N/A") ? "0" : nota3),
                              Double.parseDouble(nota4.equals("N/A") ? "0" : nota4)};
            for (double nota : notas) {
                if (nota > 0) {
                    media += nota;
                    notasValidas++;
                }
            }
            media = notasValidas > 0 ? Math.round((media / notasValidas) * 100.0) / 100.0 : 0;

            // Adiciona as linhas na tabela
            modeloTabela.addRow(new String[]{disciplina, nota1, nota2, nota3, nota4, String.valueOf(media)});
        }

        // Adicionar os componentes ao frame
        frame.add(labelHeader);
        frame.add(btnSair);
        frame.add(lblSaudacao);

        // Exibe o frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            frame.dispose();
            // Aqui você pode redirecionar para a tela de login
        }
    }
}
