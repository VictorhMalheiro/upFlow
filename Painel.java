package upFlow;
// ----JFreeChart -------

import org.jfree.chart.ChartFactory; //biblioteca que cria o grafico
import org.jfree.chart.ChartPanel; //biblioteca que faz o painel se registra com o gr�fico para receber notifica��es de altera��es em qualquer componente do gr�fico. O gr�fico � redesenhado automaticamente sempre que esta notifica��o � recebida.
import org.jfree.chart.JFreeChart; //Uma classe de gr�fico implementada usando as APIs Java 2D. 
import org.jfree.data.general.DefaultPieDataset; //Constr�i um novo conjunto de dados, inicialmente vazio.
import org.jfree.chart.plot.PiePlot; //Um gr�fico que exibe dados na forma de um gr�fico de pizza, usando dados de qualquer classe que implemente a interface PieDataset.
import org.jfree.data.general.DefaultPieDataset; //Constr�i um novo conjunto de dados, inicialmente vazio.

//-----------------------
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.text.DecimalFormat;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.BorderLayout;

// ------- Banco de Dados ------//
import java.sql.*; //Fornece a API para acessar e processar dados armazenados em uma fonte de dados (geralmente um banco de dados relacional) usando a linguagem de programa��o JavaTM.
import java.sql.DriverManager;//O servi�o b�sico para gerenciar um conjunto de drivers JDBC.
import java.sql.Connection; //Uma conex�o (sess�o) com um banco de dados espec�fico. As instru��es SQL s�o executadas e os resultados s�o retornados dentro do contexto de uma conex�o.
import java.sql.PreparedStatement; //Um objeto que representa uma instru��o SQL pr�-compilada.
import java.sql.ResultSet; // Uma tabela de dados que representa um conjunto de resultados do banco de dados, que geralmente � gerado pela execu��o de uma instru��o que consulta o banco de dados.
import java.sql.SQLException; // Uma exce��o que fornece informa��es sobre um erro de acesso ao banco de dados ou outros erros.


class Painel extends JFrame implements ActionListener{
	/*
	 *	Criar Ambiente de Cadastro da Empresa
	 *	Para colocar Informa��es - Nome, Email, Redes Sociais, Telefone...
	 *
	 *	PREFIXOS DE VARIAVEIS:
	 *	JMenuItem = mi
	 *	+Representa��o
	 **/
	//JPanel p1 = new JPanel();
	JMenu menuLead,menuCampanhas, menuLandingPage, menuEmpresa;
	JMenuItem miLeads,miEditarLead,miSairSistema,miVerCampanhas,miCriarCampanha,miCadastrarLead, miCriarLandingPage, miVerEmpresa, miCadastroEmpresa;
	JMenuBar MenuBar1;
	private Image icone; //image � uma classe e icone � o objeto desta classe!
	private JLabel statusBar;
	int ds,dia,mes,ano;
	ResultSet resultSet;
	Statement statement;
	
	JTextField jtfNome, jtfEmail, jtfIdade;
	JLabel jlNome, jlEmail, jlIdade, jlVariavel;
	JButton btnCadastrar, btnLogar;
	Calendar data;
	
	String diasemana[]= {"Domingo","Segunda - Feira","Ter�a - Feira","Quarta - Feira","Quinta - Feira","Sexta - Feira","S�bado"};
	String meses[] = {"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	
	
	

	public static void main(String args[]) {
		
		JFrame janela = new Painel();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new FlowLayout(FlowLayout.LEFT));
		janela.setVisible(true);
			
	}
	
	Painel(){
		
		
		super("upFlow - Sistema de Gerenciamento de Marketing");
		Container tela = getContentPane();
		setSize(800,600);
		setLocationRelativeTo(null);
		
		ImageIcon icone = new ImageIcon("icon-upFlow@72ppi.png");
		setIconImage(icone.getImage());
		setResizable(true);
		
		menu();
		qtdFB();
		qtdInsta();
		criaGrafico();
	
		infoHorario();
				
		getContentPane().setBackground(new Color(255, 255, 255));
		//75, 88, 164    64, 249, 155
		UIManager.put("OptionPane.background", new Color(47, 205, 154));
		UIManager.getLookAndFeelDefaults().put("Panel.background", new Color(47, 205, 154));
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == miLeads){
			JFrame tab = new Tabela();
			tab.setVisible(true);
		}
		
		if(e.getSource() == btnCadastrar) {
			String nome = jtfNome.getText();
			JOptionPane.showMessageDialog(null,nome+" Lead Cadastrado!");
		}
		
		
		if(e.getSource() == miCadastrarLead) {
			JFrame cad = new CadastroLead();
			cad.setVisible(true);
		}
		if(e.getSource() == miCriarLandingPage) {
			JFrame criarLand = new CriarLandingPage();
			criarLand.setVisible(true);
		}
		if(e.getSource() == miCadastroEmpresa) {
			JFrame cadEmpresa = new CadastroEmpresa();
			cadEmpresa.setVisible(true);
		}
		if(e.getSource() == miSairSistema) {
			int opcao;
			Object[] botoes = {"Sim","N�o"};
			opcao = JOptionPane.showOptionDialog(null,"Deseja mesmo fechar a janela?","Fechar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,botoes,botoes[0]);
			if(opcao == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(e.getSource() == miCriarCampanha) {
			JFrame cadCampanha = new CadastroCampanha();
			cadCampanha.setVisible(true);
		}
		
	}
	
	public void infoHorario(){
		data = Calendar.getInstance();
		
		ds = data.get(Calendar.DAY_OF_WEEK);
		dia = data.get(Calendar.DAY_OF_MONTH);
		mes = data.get(Calendar.MONTH);
		ano = data.get(Calendar.YEAR);
		
		statusBar = new JLabel("Barretos, Hoje �: "+diasemana[ds-1]+", "+dia+" de "+meses[mes]+" de "+ano);
		add(statusBar,BorderLayout.SOUTH);
		statusBar.setForeground(Color.black);
	}
	
	public void menu(){
		MenuBar1 = new JMenuBar();
		MenuBar1.setBackground(new Color(75,88,164));
		MenuBar1.setPreferredSize(new java.awt.Dimension(56, 40));
		MenuBar1.setBorder(null);
		menuLead = new JMenu("Leads");
		menuLead.setForeground(new Color(255,255,255));
		menuEmpresa = new JMenu("Ver empresa");
		menuEmpresa.setForeground(new Color(255,255,255));		
		menuCampanhas = new JMenu("Campanhas");
		menuCampanhas.setForeground(new Color(255,255,255));
		//--		
		menuLandingPage = new JMenu("Landing Pages");
		menuLandingPage.setForeground(new Color(255,255,255));
		
		miLeads = new JMenuItem("Ver leads",new ImageIcon("imagens/icons8_Member_16px.png"));
		miLeads.addActionListener(this);
		miLeads.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
		miLeads.setMnemonic(KeyEvent.VK_P);
		
		miCadastrarLead = new JMenuItem("Cadastrar Leads",new ImageIcon("imagens/icons8_Add_Lead16px.png"));
		miCadastrarLead.addActionListener(this);
		miCadastrarLead.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.ALT_MASK));
		miCadastrarLead.setMnemonic(KeyEvent.VK_F);
		
		miEditarLead = new JMenuItem("Editar Leads",new ImageIcon("medicamentos.gif"));
		miEditarLead.addActionListener(this);
		miEditarLead.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.ALT_MASK));
		miEditarLead.setMnemonic(KeyEvent.VK_M);
		
		miSairSistema = new JMenuItem("Sair do Sistema",new ImageIcon("excluir.png"));
		miSairSistema.addActionListener(this);
		miSairSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		miSairSistema.setMnemonic(KeyEvent.VK_S);
		
		miCadastrarLead = new JMenuItem("Cadastrar Leads",new ImageIcon("imagens/icons8_Add_Lead16px.png"));
		miCadastrarLead.addActionListener(this);
		miCadastrarLead.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.ALT_MASK));
		miCadastrarLead.setMnemonic(KeyEvent.VK_F);
		
		menuLead.add(miLeads);
		menuLead.add(miCadastrarLead);
		menuLead.add(miEditarLead);
		menuLead.addSeparator();
		menuLead.add(miSairSistema);
		
		miVerCampanhas = new JMenuItem("Ver Campanhas", new ImageIcon("relatorio.gif"));
		miVerCampanhas.addActionListener(this);
		miVerCampanhas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
		miVerCampanhas.setMnemonic(KeyEvent.VK_R);
		
		miCriarCampanha = new JMenuItem("Criar Campanha",new ImageIcon("relatorio.gif"));
		miCriarCampanha.addActionListener(this);
		miCriarCampanha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
		miCriarCampanha.setMnemonic(KeyEvent.VK_E);
		
		miCriarLandingPage = new JMenuItem("Criar Landing Page",new ImageIcon("imagens/icons8_Open_in_Browser_48px.png"));
		miCriarLandingPage.addActionListener(this);
		miCriarLandingPage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		miCriarLandingPage.setMnemonic(KeyEvent.VK_L);
		menuLandingPage.add(miCriarLandingPage);
		
		menuCampanhas.add(miVerCampanhas);
		menuCampanhas.add(miCriarCampanha);		
		
		miCadastroEmpresa = new JMenuItem("Cadastrar Empresa",new ImageIcon("imagens/icons8_Member_16px.png"));
		miCadastroEmpresa.addActionListener(this);
		miCadastroEmpresa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.ALT_MASK));
		miCadastroEmpresa.setMnemonic(KeyEvent.VK_U);
			
		miVerEmpresa = new JMenuItem("Ver Empresa",new ImageIcon("imagens/icons8_Member_16px.png"));
		miVerEmpresa.addActionListener(this);
		miVerEmpresa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.ALT_MASK));
		miVerEmpresa.setMnemonic(KeyEvent.VK_V);
		
		menuEmpresa.add(miCadastroEmpresa);
		menuEmpresa.add(miVerEmpresa);
		
		MenuBar1.add(menuLead);
		MenuBar1.add(menuCampanhas);
		MenuBar1.add(menuLandingPage);
		MenuBar1.add(menuEmpresa);
		setJMenuBar(MenuBar1);
	}
	
	
	int facebook = 0;
	int instagram = 0;
	public void criaGrafico(){
		
		// ----- JFreeChart ----- //
		DefaultPieDataset pizza = new DefaultPieDataset();
		pizza.setValue("Facebook", facebook);
		pizza.setValue("Instagram", instagram);
		//pizza.setValue("LinkedIn", 586);
		//pizza.setValue("Youtube", 3567);
		//pizza.setValue("Email", 7864);
		
		JFreeChart grafico = ChartFactory.createPieChart("Quantidade de Leads por Rede",pizza,true,true,false);
	//	JFreeChart grafico = ChartFactory.create
			//---- MUDANDO CORES ----- //
			
				PiePlot fatia = (PiePlot) grafico.getPlot();
				fatia.setSectionPaint("Facebook", new Color(66, 103, 178));
				//fatia.setSectionPaint("Instagram", new Color(195, 42, 163));
				//fatia.setSectionPaint("LinkedIn", new Color(0, 119, 181));
				//fatia.setSectionPaint("Email", new Color(251, 188, 5));
				//fatia.setSectionPaint("Youtube", new Color(255, 0, 0));
				
			
			//------
		ChartPanel painel = new ChartPanel(grafico);
		add(painel);
		//----------/// --------------
	}
	

	
	public void qtdFB(){
		//resultSet = statement.executeQuery("SELECT COUNT(*) FROM lead WHERE user_fb NOT IS NULL");
		String url = "jdbc:mysql://127.0.0.1:3306/upflow?useSSL=false";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection minhaConexao = DriverManager.getConnection(url,"root","");
			statement = minhaConexao.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);;
			resultSet = statement.executeQuery("SELECT * FROM lead WHERE user_fb IS NOT NULL");
			resultSet.last();
			facebook = resultSet.getRow();
		}catch(ClassNotFoundException erro){
			System.out.println("Driver nao encontrado");
		}
		catch(SQLException erro){				
			System.out.println("Problemas na conexao com a fonte de dados"); 
		}
		
//		return linhasFB;
	}
	public void qtdInsta(){
		//resultSet = statement.executeQuery("SELECT COUNT(*) FROM lead WHERE user_fb NOT IS NULL");
		String url = "jdbc:mysql://127.0.0.1:3306/upflow?useSSL=false";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection minhaConexao = DriverManager.getConnection(url,"root","");
			statement = minhaConexao.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);;
			resultSet = statement.executeQuery("SELECT * FROM lead WHERE user_insta IS NOT NULL");
			resultSet.last();
			instagram = resultSet.getRow();
		}catch(ClassNotFoundException erro){
			System.out.println("Driver nao encontrado");
		}
		catch(SQLException erro){				
			System.out.println("Problemas na conexao com a fonte de dados"); 
		}
		
//		return linhasFB;
	}
	
	public void carregaResultSet(){
		//String url="jdbc:mysql//localhost:3306/upflow";
		String url="jdbc:mysql://127.0.0.1:3306/upflow";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection minhaConexao = DriverManager.getConnection(url,"root","");
			statement = minhaConexao.createStatement(resultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery("SELECT * FROM lead");
		//qtdFBl = statement.executeQuery("SELECT * FROM lead WHERE user_fb NOT IS NULL");
		}catch(ClassNotFoundException erro){
			System.out.print("Driver n�o encontrado");
		}catch(SQLException ero){
			System.out.println("Problemas na conexao com a fonte de dados");
		}
	}
		
}
