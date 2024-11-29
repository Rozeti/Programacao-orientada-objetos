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

public class JanelaLogin implements ActionListener{
	
	JButton alunoButton;
	JButton professorButton;
	JFrame frame;
	
	JanelaLogin(){
		frame = new JFrame();
		
		ImageIcon iconeWindow = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");// Icone da Janela
		
		//------Criação do Frame-------
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prime Scholars Academy");
		frame.setSize(350, 500);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(0x194A70));
		frame.setIconImage(iconeWindow.getImage());
		
		//------Criação do da Label do Header-------
		JLabel labelHeader = new JLabel();
		
		ImageIcon iconeHeaderOriginal = new ImageIcon("assets\\img\\Universidade Ensino Superior Logo (1).png");
		Image iconeRedimensionado = iconeHeaderOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon iconeHeader = new ImageIcon(iconeRedimensionado);
		
		labelHeader.setBounds(0, 0, 350, 80);
		labelHeader.setIcon(iconeHeader);
		labelHeader.setText("Prime Scholars Academy");
		labelHeader.setFont(new Font("Comic Sans", Font.BOLD, 18));
		labelHeader.setForeground(new Color(0xBC994E));
		labelHeader.setBackground(new Color(0x194A70));
		labelHeader.setOpaque(true);
		
		//------Criação das sessão Body-------
		
		//--Label do texto "Qual o seu Perfil"
		JLabel labelPerfil = new JLabel();
		labelPerfil.setText("Qual o seu Perfil: ");
		labelPerfil.setBounds(97, 80, 156, 20);
		labelPerfil.setFont(new Font("Comic Sans", Font.BOLD, 18));
		labelPerfil.setForeground(new Color(0xBC994E));
		labelPerfil.setBackground(new Color(0x194A70));
		labelPerfil.setOpaque(true);
		
		//--Botão do Aluno
		alunoButton = new JButton();
		
		ImageIcon iconeAlunoOriginal = new ImageIcon("assets\\img\\aluna.png");
		Image iconeAlunoRedimensionado = iconeAlunoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon iconeAluno = new ImageIcon(iconeAlunoRedimensionado);
		
		alunoButton.setBounds(97, 110, 156, 150);
		alunoButton.setBorderPainted(true);
		alunoButton.setIcon(iconeAluno);
		alunoButton.setText("Aluno");
		alunoButton.setFocusable(false);
		alunoButton.setFont(new Font("Comic Sans", Font.BOLD, 18));
		alunoButton.setForeground(new Color(0xBC994E));
		alunoButton.setBackground(new Color(0x0A273D));
		alunoButton.setHorizontalTextPosition(alunoButton.CENTER);
		alunoButton.setVerticalTextPosition(alunoButton.BOTTOM);
		alunoButton.addActionListener(this);
		
		//--Botão do Professor
		professorButton = new JButton();
				
		ImageIcon iconeProfessorOriginal = new ImageIcon("assets\\img\\professor.png");
		Image iconeProfessorRedimensionado = iconeProfessorOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon iconeProfessor = new ImageIcon(iconeProfessorRedimensionado);
			
		professorButton = new JButton();
		professorButton.setBounds(97, 270, 156, 160);
		professorButton.setBorderPainted(true);
		professorButton.setIcon(iconeProfessor);
		professorButton.setText("Professor");
		professorButton.setFocusable(false);
		professorButton.setFont(new Font("Comic Sans", Font.BOLD, 18));
		professorButton.setForeground(new Color(0xBC994E));
		professorButton.setBackground(new Color(0x0A273D));
		professorButton.setHorizontalTextPosition(professorButton.CENTER);
		professorButton.setVerticalTextPosition(professorButton.BOTTOM);
		professorButton.addActionListener(this);
		
		
		frame.add(labelHeader);
		frame.add(labelPerfil);
		frame.add(alunoButton);
		frame.add(professorButton);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == alunoButton) {
			frame.dispose();
			TelaLoginAluno telaDoAluno = new TelaLoginAluno();
		}else if(e.getSource() == professorButton) {
			frame.dispose();
			TelaLoginProfessor telaDoProfessor = new TelaLoginProfessor();
		}
		
	}
}
