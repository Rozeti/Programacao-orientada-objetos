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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.TelaCadastroProfessorDAO;

import javax.swing.JPasswordField;

public class TelaCadastroProfessor implements ActionListener {

    private JFrame frame;
    private JButton cadastrar;
    private JButton voltar;
    private JTextField caixaNome;
    private JTextField caixaMatricula;
    private JTextField caixaFormacao;
    private JTextField caixaEndereco;
    private JTextField caixaNum;
    private JTextField campoDisciplina;
    private JTextField campoDepartamento;
    private JPasswordField caixaSenha;

    public TelaCadastroProfessor() {
        frame = new JFrame();

        ImageIcon iconeWindow = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Prime Scholars Academy");
        frame.setSize(350, 750);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0x194A70));
        frame.setIconImage(iconeWindow.getImage());

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

        voltar = new JButton("Voltar");
        voltar.setBounds(260, 25, 70, 30);
        voltar.setFocusable(false);
        voltar.setFont(new Font("Comic Sans", Font.PLAIN, 12));
        voltar.setBackground(new Color(0x0A273D));
        voltar.setForeground(Color.white);
        voltar.addActionListener(this);

        JLabel labelPerfil = new JLabel("Cadastro do Professor");
        labelPerfil.setBounds(64, 80, 200, 20);
        labelPerfil.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelPerfil.setForeground(new Color(0xBC994E));
        labelPerfil.setBackground(new Color(0x194A70));
        labelPerfil.setOpaque(true);

        JLabel tituloNome = new JLabel("Nome do Professor:");
        tituloNome.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloNome.setForeground(new Color(0xBC994E));
        tituloNome.setBounds(20, 120, 200, 30);
        caixaNome = new JTextField();
        caixaNome.setBounds(10, 145, 310, 30);
        caixaNome.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloMatricula = new JLabel("Matricula do Professor:");
        tituloMatricula.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloMatricula.setForeground(new Color(0xBC994E));
        tituloMatricula.setBounds(20, 185, 200, 30);
        caixaMatricula = new JTextField();
        caixaMatricula.setBounds(10, 210, 310, 30);
        caixaMatricula.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloFormacao = new JLabel("Formação do Professor:");
        tituloFormacao.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloFormacao.setForeground(new Color(0xBC994E));
        tituloFormacao.setBounds(20, 250, 200, 30);
        caixaFormacao = new JTextField();
        caixaFormacao.setBounds(10, 275, 310, 30);
        caixaFormacao.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloEndereco = new JLabel("Endereço do Professor:");
        tituloEndereco.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloEndereco.setForeground(new Color(0xBC994E));
        tituloEndereco.setBounds(20, 315, 200, 30);
        caixaEndereco = new JTextField();
        caixaEndereco.setBounds(10, 340, 310, 30);
        caixaEndereco.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloNum = new JLabel("Número de Contato:");
        tituloNum.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloNum.setForeground(new Color(0xBC994E));
        tituloNum.setBounds(20, 380, 200, 30);
        caixaNum = new JTextField();
        caixaNum.setBounds(10, 405, 310, 30);
        caixaNum.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloDisciplina = new JLabel("Disciplina Ministrada:");
        tituloDisciplina.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloDisciplina.setForeground(new Color(0xBC994E));
        tituloDisciplina.setBounds(20, 445, 180, 30);
        campoDisciplina = new JTextField();
        campoDisciplina.setBounds(10, 470, 310, 30);
        campoDisciplina.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloDepartamento = new JLabel("Departamento de Filiação:");
        tituloDepartamento.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloDepartamento.setForeground(new Color(0xBC994E));
        tituloDepartamento.setBounds(20, 510, 200, 30);
        campoDepartamento = new JTextField();
        campoDepartamento.setBounds(10, 535, 310, 30);
        campoDepartamento.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        JLabel tituloSenha = new JLabel("Senha:");
        tituloSenha.setFont(new Font("Comic Sans", Font.BOLD, 17));
        tituloSenha.setForeground(new Color(0xBC994E));
        tituloSenha.setBounds(20, 575, 200, 30);
        caixaSenha = new JPasswordField();
        caixaSenha.setBounds(10, 600, 310, 30);
        caixaSenha.setFont(new Font("Comic Sans", Font.PLAIN, 17));

        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(100, 650, 140, 40);
        cadastrar.setFocusable(false);
        cadastrar.setFont(new Font("Comic Sans", Font.BOLD, 20));
        cadastrar.setBackground(new Color(0x216700));
        cadastrar.setForeground(Color.white);
        cadastrar.addActionListener(this);

        frame.add(voltar);
        frame.add(labelHeader);
        frame.add(labelPerfil);
        frame.add(tituloNome);
        frame.add(caixaNome);
        frame.add(tituloMatricula);
        frame.add(caixaMatricula);
        frame.add(tituloFormacao);
        frame.add(caixaFormacao);
        frame.add(tituloEndereco);
        frame.add(caixaEndereco);
        frame.add(tituloNum);
        frame.add(caixaNum);
        frame.add(tituloDisciplina);
        frame.add(campoDisciplina);
        frame.add(tituloDepartamento);
        frame.add(campoDepartamento);
        frame.add(tituloSenha);
        frame.add(caixaSenha);
        frame.add(cadastrar);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            frame.dispose();
            new TelaLoginProfessor();
        } else if (e.getSource() == cadastrar) {
            String nome = caixaNome.getText();
            String matriculaTexto = caixaMatricula.getText();
            String formacao = caixaFormacao.getText();
            String endereco = caixaEndereco.getText();
            String numeroContato = caixaNum.getText();
            String disciplina = campoDisciplina.getText();
            String departamentoSelecionado = campoDepartamento.getText();
            String senha = new String(caixaSenha.getPassword());

            if (nome.isEmpty() || matriculaTexto.isEmpty() || formacao.isEmpty() || endereco.isEmpty() ||
                numeroContato.isEmpty() || senha.isEmpty() || disciplina.isEmpty() || departamentoSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!");
                return;
            }

            try {
                int matricula = Integer.parseInt(matriculaTexto);
                new TelaCadastroProfessorDAO().cadastrarProfessor(nome, matricula, formacao, endereco, numeroContato,
                        disciplina, departamentoSelecionado, senha);
                JOptionPane.showMessageDialog(frame, "Professor cadastrado com sucesso!");
                new TelaLoginProfessor();
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao cadastrar professor: " + ex.getMessage());
            }
        }
    }
}
