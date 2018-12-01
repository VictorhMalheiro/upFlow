package upFlow;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class Login extends JFrame  implements ActionListener{
	
	JLabel jlIcone;
	JLabel rotulouserName,rotulosenha;
	JPasswordField jpsenha;
	JButton btnEntrar;
	JTextField userName;
	String username = null;
    String senha = null;
	
	ResultSet resultSet;
	Statement statement;
	
	
	public Login(){
		
		super("upFlow - Faça login");
		Container tela = getContentPane();
		ImageIcon icone = new ImageIcon("icon-upFlow@72ppi.png");
		setIconImage(icone.getImage());
		setLayout(null);
		getContentPane().setBackground(new Color(55,65,120));		
		setLocationRelativeTo(null);
		
		rotulouserName = new JLabel("Usuario: ");
		rotulouserName.setBounds(50,200,100,20);
		rotulouserName.setForeground(new java.awt.Color(229,229,229));
		
		rotulosenha = new JLabel("Senha:");
		rotulosenha.setBounds(50,242,100,20);
		rotulosenha.setForeground(new java.awt.Color(229,229,229));
		
		
		//Campo de Usuario
		userName = new JTextField();
		userName.setBounds(50,220,250,25);
		userName.setBackground(new Color(46,54,99));
		userName.setForeground(new java.awt.Color(229,229,229));
		userName.setBorder(null);
		tela.add(rotulouserName);
		tela.add(userName);
		
		//Campo de Senha
		jpsenha = new JPasswordField();
		jpsenha.setBounds(50,260,250,25);
		jpsenha.setBackground(new Color(46,54,99));
		jpsenha.setForeground(new java.awt.Color(229,229,229));
		jpsenha.setBorder(null);
		tela.add(rotulosenha);
		tela.add(jpsenha);
		
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(50,300,250,45);
		btnEntrar.addActionListener(this);
		btnEntrar.setBorder(null);
		btnEntrar.setBackground(new Color(64, 249, 155));
		tela.add(btnEntrar);
		
		setSize(360,640);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
//		carregaResultSet();
		
	}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnEntrar) {
	        	carregaResultSet();
	        	if(pass == 1){
					UIManager.put("OptionPane.background", new Color(64, 249, 155));
		            UIManager.getLookAndFeelDefaults().put("Panel.background", new Color(64, 249, 155));
		            JOptionPane.showMessageDialog(null,"Conectado");
		            
		            Painel janela = new Painel();
		            janela.setLayout(new FlowLayout(FlowLayout.LEFT));
		            janela.setVisible(true);
		         	janela.setResizable(false);
		         	dispose();
				}else{
		        	UIManager.put("OptionPane.background", new Color(234, 189, 39));
		            UIManager.getLookAndFeelDefaults().put("Panel.background", new Color(234, 189, 39));
		            JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto, tente novamente.","Não foi possivel acessar o sistema", JOptionPane.ERROR_MESSAGE);
		       	}
	        }  
		}
    	
	int pass = 0;
	public void carregaResultSet(){
			String url = "jdbc:mysql://127.0.0.1:3306/upflow?useSSL=false";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection minhaConexao = DriverManager.getConnection(url,"root","");
				statement = minhaConexao.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);;
												  //SELECT * FROM usuario WHERE `username` = 'bina' AND `senha` = 'vina123'
				resultSet = statement.executeQuery("SELECT * FROM usuario WHERE `username` = '"+userName.getText()+"' AND `senha` = '"+jpsenha.getText()+"'");
				
				resultSet.last();
				pass = resultSet.getRow();
			}catch(ClassNotFoundException erro){
				System.out.println("Driver nao encontrado");
			}
			catch(SQLException erro){				
				System.out.println("Problemas na conexao com a fonte de dados"); 
			}
	}
	
	
	public static void main(String args[]){				
		Login app = new Login();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}