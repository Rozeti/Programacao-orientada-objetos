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

import DAO.TelaPrincipalAlunoDAO;

public class TelaPrincipalAluno implements ActionListener {
    JFrame frame;
    JButton sair;
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
        sair = new JButton("Sair");
        sair.setBounds(350, 25, 70, 30);
        sair.setFocusable(false);
        sair.setFont(new Font("Comic Sans", Font.PLAIN, 12));
        sair.setBackground(new Color(0x0A273D));
        sair.setForeground(Color.white);
        sair.addActionListener(this);

        // Recupera o primeiro nome do aluno com base na matrícula
        String primeiroNome = telaPrincipalAlunoDAO.getPrimeiroNomePorMatricula(matricula);

        // Título com saudação
        JLabel labelPerfil = new JLabel("Olá, " + primeiroNome);
        labelPerfil.setBounds(120, 80, 220, 20);
        labelPerfil.setFont(new Font("Comic Sans", Font.BOLD, 20));
        labelPerfil.setForeground(new Color(0xBC994E));
        labelPerfil.setBackground(new Color(0x194A70));
        labelPerfil.setOpaque(true);

        JLabel labelLinha = new JLabel();
        labelLinha.setBounds(10, 105, 415, 3);
        labelLinha.setForeground(new Color(0xBC994E));
        labelLinha.setBackground(new Color(0x0A273D));
        labelLinha.setOpaque(true);

        // Corpo da sessão
        JPanel panelMateriaNotas = new JPanel();
        panelMateriaNotas.setFont(new Font("Comic Sans", Font.PLAIN, 18));
        panelMateriaNotas.setForeground(Color.black);
        panelMateriaNotas.setBackground(new Color(0xBC994E));
        panelMateriaNotas.setOpaque(true);
        panelMateriaNotas.setLayout(null);

        // Adicionar títulos das colunas
        JLabel[] colunas = {
            new JLabel("Disciplinas"), new JLabel("1ª Nota"),
            new JLabel("2ª Nota"), new JLabel("3ª Nota"), new JLabel("4ª Nota")
        };
        int[] colunasPosX = {10, 150, 215, 280, 345};
        for (int i = 0; i < colunas.length; i++) {
            colunas[i].setBounds(colunasPosX[i], 10, 100, 20);
            colunas[i].setFont(new Font("Comic Sans", Font.BOLD, 13));
            panelMateriaNotas.add(colunas[i]);
        }

        // Recuperar dados das matérias e notas com base na matrícula
        List<String[]> materiasNotas = telaPrincipalAlunoDAO.getMateriasNotasPorMatricula(matricula);

        // Renderizar os dados no painel
        int posY = 40;
        for (String[] materiaNota : materiasNotas) {
            JLabel materiaLabel = new JLabel(materiaNota[0]);
            materiaLabel.setBounds(10, posY, 130, 20);
            materiaLabel.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            materiaLabel.setOpaque(true);
            panelMateriaNotas.add(materiaLabel);

            for (int i = 1; i <= 4; i++) {
                String nota = (materiaNota[i] == null || materiaNota[i].isEmpty()) ? "N/A" : materiaNota[i];
                JLabel notaLabel = new JLabel(nota);
                notaLabel.setBounds(150 + (i - 1) * 65, posY, 50, 20);
                notaLabel.setFont(new Font("Comic Sans", Font.PLAIN, 15));
                notaLabel.setOpaque(true);
                panelMateriaNotas.add(notaLabel);
            }
            posY += 30;
        }

        // Ajustar o tamanho do painel de acordo com os dados
        panelMateriaNotas.setPreferredSize(new java.awt.Dimension(400, posY));

        // Adiciona o JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelMateriaNotas);
        scrollPane.setBounds(10, 120, 410, 600);
        frame.add(scrollPane);

        // Adicionar os componentes ao frame
        frame.add(labelHeader);
        frame.add(sair);
        frame.add(labelPerfil);
        frame.add(labelLinha);

        // Exibe o frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            frame.dispose();
            // Redirecionar para tela de login (implementar conforme necessário)
        }
    }
}
