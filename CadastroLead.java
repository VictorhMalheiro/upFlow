package upFlow;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import java.sql.*;

public class CadastroLead extends JFrame  implements ActionListener{
		  //id_lead	nome	email	user_fb	user_insta
	JLabel jlIcone, jlNome, jlEmail, jlUserFb, jlUserInsta, jlID_Lead;
	JLabel rotulouserName,rotulosenha, rotuloEmail;
	JTextField jtfNome, jtfEmail, jtfUserFb, jtfUserInsta, jtfID_Lead;
	JPasswordField jpsenha;
	JButton btnCadastrar;	
	
	ResultSet resultSet;
	Statement statement;

	public CadastroLead(){
		
		super("Cadastro de Leads - upFlow");
		Container tela = getContentPane();
		tela.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImageIcon icone = new ImageIcon("icon-upFlow@72ppi.png");
		setIconImage(icone.getImage());
		setLayout(null);
		getContentPane().setBackground(new Color(237, 242, 248));		
	//setLocationRelativeTo(null);
		
		//Painel cPainel = new Painel();
		//tela.add(cPainel.menu());
		
		//JButton.setBackground(new Color(75, 88, 164));
	//--------------CRIANDO CAMPOS DE INSER��O DE DADOS ------INICIO-------	
		//Campo nome --------------
		jlNome = new JLabel("Nome e Sobrenome");
	//  jlNome.setBounds(x, y, l,a);
		jlNome.setBounds(30,30,150, 20);
		tela.add(jlNome);
		
		jtfNome = new JTextField(150);
		jtfNome.setBounds(150,30,300,20);
		tela.add(jtfNome);
		
		//Campo nome --------FIM
		
				//Campo Email --------------
		jlEmail = new JLabel("Email");
		jlEmail.setBounds(30,60,150, 20);
		tela.add(jlEmail);
		
		jtfEmail = new JTextField(150);
		jtfEmail.setBounds(150,60,300,20);
		tela.add(jtfEmail);
		
		//Campo Email --------FIM
		

		//Campo User FB --------------
		jlUserFb = new JLabel("Usuario Facebook");
		jlUserFb.setBounds(30,120,150, 20);
		tela.add(jlUserFb);
		
		jtfUserFb = new JTextField(20);
		jtfUserFb.setBounds(150,120,300,20);
		tela.add(jtfUserFb);
		
		//Campo User FB --------------
		jlUserInsta = new JLabel("Usuario Instagram");
		jlUserInsta.setBounds(30,150,150, 20);
		tela.add(jlUserInsta);
		
		jtfUserInsta = new JTextField(20);
		jtfUserInsta.setBounds(150,150,300,20);
		tela.add(jtfUserInsta);
		//-------------------------------------


		//Campo ID User --------------
		jlID_Lead = new JLabel("ID do Usuario");
		jlID_Lead.setBounds(30,200,150, 20);
		tela.add(jlID_Lead);
		
		jtfID_Lead = new JTextField(20);
		jtfID_Lead.setBounds(150,200,300,20);
		tela.add(jtfID_Lead);
		//-------------------------------------
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(97,0,237));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBounds(150,250,300,35);
		btnCadastrar.addActionListener(this);
		tela.add(btnCadastrar);
		
		//Campo Email --------FIM
		
	//------// FIM //--------CRIANDO CAMPOS DE INSER��O DE DADOS -------------
		
		
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);
		carregaResultSet();
}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCadastrar) {
			try {
				//id_lead	nome	email	user_fb	user_insta
				String sql = "INSERT INTO lead(id_lead,nome,email,user_fb,user_insta)Values('"+
				jtfID_Lead.getText()+"','"+
				jtfNome.getText()+"','"+
				jtfEmail.getText()+"','"+
				jtfUserFb.getText()+"','"+
				jtfUserInsta.getText()+"')";
				statement.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"Lead Cadastrado com sucesso!");
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null,"ERRO NA GRAVACAO");
			}
			
		//	JOptionPane.showMessageDialog(null,"Lead cadastrado com sucesso!","+1 um lead no sistema");
		}
	}
	
	public static void main(String args[]){
				
		CadastroLead app = new CadastroLead();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
	public void carregaResultSet(){
		//String
		//	url="jdbc:mysql://localhost:3306/upflow";
		String url = "jdbc:mysql://127.0.0.1:3306/upflow?useSSL=false";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection minhaConexao = DriverManager.getConnection(url,"root","");
			statement = minhaConexao.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);;
			resultSet = statement.executeQuery("SELECT * FROM lead");
		}catch(ClassNotFoundException erro){
			System.out.println("Driver nao encontrado");
		}
		catch(SQLException erro){				
			System.out.println("Problemas na conexao com a fonte de dados"); 
		}
	}
}