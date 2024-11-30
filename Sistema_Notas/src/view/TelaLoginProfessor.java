package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.ProfessorDAO;

public class TelaLoginProfessor implements ActionListener {

    // Declaração das variáveis globais
    JFrame frame;
    JTextField caixaMatricula;
    JPasswordField caixaSenha;
    JButton cadastrar;
    JButton entrar;
    JButton voltar;

    // Construtor da classe
    public TelaLoginProfessor() {
        frame = new JFrame();

        ImageIcon iconeWindow = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png"); // Ícone da janela

        //------Criação do Frame-------
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Prime Scholars Academy");
        frame.setSize(350, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0x194A70));
        frame.setIconImage(iconeWindow.getImage());

        //------Criação do Header-------
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

        //------Criação das sessões do corpo-------
        // Imagem de Login
        ImageIcon fotoLoginOriginal = new ImageIcon("assets\\img\\person (1).png");
        Image fotoLoginRedimensionado = fotoLoginOriginal.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon fotoLogin = new ImageIcon(fotoLoginRedimensionado);

        JLabel labelFoto = new JLabel();
        labelFoto.setIcon(fotoLogin);
        labelFoto.setBounds(120, 75, 110, 110);
        labelFoto.setHorizontalAlignment(JLabel.CENTER);
        labelFoto.setVerticalAlignment(JLabel.CENTER);

        // Caixa de texto para matrícula
        JLabel tituloMatricula = new JLabel("Matrícula do Professor: ");
        tituloMatricula.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloMatricula.setForeground(new Color(0xBC994E));
        tituloMatricula.setBounds(20, 180, 200, 30);

        caixaMatricula = new JTextField();
        caixaMatricula.setBounds(10, 205, 310, 30);
        caixaMatricula.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        // Caixa de senha
        JLabel tituloSenha = new JLabel("Senha: ");
        tituloSenha.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloSenha.setForeground(new Color(0xBC994E));
        tituloSenha.setBounds(20, 245, 180, 30);

        caixaSenha = new JPasswordField();
        caixaSenha.setBounds(10, 275, 310, 30);
        caixaSenha.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        // ComboBox para selecionar disciplina
        JLabel tituloComboBox = new JLabel("");
        tituloComboBox.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloComboBox.setForeground(new Color(0xBC994E));
        tituloComboBox.setBounds(20, 315, 180, 30);

        String[] disciplina = {"Matemática", "Biologia", "Física", "Química", "História", "Geografia", "Artes", "Educação Física",
                                "Língua Inglesa", "Língua Alemã", "Língua Francesa", "Língua Portuguesa", "Empreendedorismo"};

        JComboBox<String> materia = new JComboBox<>(disciplina);
        materia.setBounds(10, 345, 310, 30);

        // Botão de cadastrar
        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(15, 345, 140, 40);
        cadastrar.setFocusable(false);
        cadastrar.setFont(new Font("Comic Sans", Font.BOLD, 20));
        cadastrar.setBackground(new Color(0xCD4800));
        cadastrar.setForeground(Color.white);
        cadastrar.addActionListener(this);

        // Botão de entrar
        entrar = new JButton("Entrar");
        entrar.setBounds(180, 345, 140, 40);
        entrar.setFocusable(false);
        entrar.setFont(new Font("Comic Sans", Font.BOLD, 20));
        entrar.setBackground(new Color(0x216700));
        entrar.setForeground(Color.white);
        entrar.addActionListener(this);

        // Botão de voltar
        voltar = new JButton("Voltar");
        voltar.setBounds(260, 25, 70, 30);
        voltar.setFocusable(false);
        voltar.setFont(new Font("Comic Sans", Font.PLAIN, 12));
        voltar.setBackground(new Color(0x0A273D));
        voltar.setForeground(Color.white);
        voltar.addActionListener(this);

        // Adicionar componentes ao frame
        frame.add(voltar);
        frame.add(labelHeader);
        frame.add(labelFoto);
        frame.add(tituloMatricula);
        frame.add(caixaMatricula);
        frame.add(tituloSenha);
        frame.add(caixaSenha);
        frame.add(tituloComboBox);
        //frame.add(materia);
        frame.add(cadastrar);
        frame.add(entrar);

        // Exibir a tela de login
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            frame.dispose();
            JanelaLogin janelaLogin = new JanelaLogin(); // Assumindo que essa classe existe para voltar à tela de login principal
        } else if (e.getSource() == cadastrar) {
            frame.dispose();
            TelaCadastroProfessor cadastroProfessor = new TelaCadastroProfessor(); // Assumindo que essa classe existe para cadastro
        } else if (e.getSource() == entrar) {
            String matriculaStr = caixaMatricula.getText();
            String senhaStr = new String(caixaSenha.getPassword());

            if (matriculaStr.isEmpty() || senhaStr.isEmpty()) {
                // Exibir uma mensagem de erro se algum campo estiver vazio
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int matricula = Integer.parseInt(matriculaStr);
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    boolean autenticado = professorDAO.autenticarProfessor(matricula, senhaStr);

                    if (autenticado) {
                        // Se as credenciais forem válidas, abre a tela principal do professor
                        JOptionPane.showMessageDialog(frame, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Fecha a tela de login
                        TelaPrincipalProfessor telaPrincipal = new TelaPrincipalProfessor(matricula); // Passa a matrícula do professor
                    } else {
                        // Se as credenciais forem inválidas, exibe uma mensagem de erro
                        JOptionPane.showMessageDialog(frame, "Matrícula ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Matrícula deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
