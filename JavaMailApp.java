package upFlow;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;


import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaMailApp extends JFrame  implements ActionListener{
	
	JLabel jlTitulo, jlAssunto, jlDestinatario, jlMensagem;
	JTextField jtfAssunto, jtfDestinatario;
	JTextArea jtaMensagem;
	JButton btnEnviar;
	
	
	 public void JavaMailApp() throws UnsupportedEncodingException {



	
	
	//JavaMailApp(){
		
		
		
		jlTitulo = new JLabel("Envio de Email");
		jlTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		jlTitulo.setForeground(new Color(75,88,164));
		jlTitulo.setBounds(10,30,200,25);
		tela.add(jlTitulo);
		
		jlAssunto = new JLabel("Assunto");
		jlAssunto.setBounds(10,60,150,25);
		tela.add(jlAssunto);
		
		jtfAssunto = new JTextField(100);
		jtfAssunto.setBounds(10,80,220,30);
		tela.add(jtfAssunto);
		
		
		jlDestinatario = new JLabel("Destinatario");
		jlDestinatario.setBounds(235,60,150,25);
		tela.add(jlDestinatario);
		
		jtfDestinatario = new JTextField(100);
		jtfDestinatario.setBounds(235,80,220,30);
		tela.add(jtfDestinatario);
		
		
		jlMensagem = new JLabel("Mensagem");
		jlMensagem.setBounds(10,115,100,25);
		tela.add(jlMensagem);
		
		jtaMensagem = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(jtaMensagem);
		jtaMensagem.setEditable(true);
		jtaMensagem.setBounds(10,135,445,150);
		tela.add(jtaMensagem);
		
		
		btnEnviar = new JButton("Enviar", new ImageIcon("imagens/48px/icons8_Send_48px.png"));
		btnEnviar.setBackground(new Color(75, 88, 164));
		btnEnviar.setForeground(new Color(255, 255, 255));
		btnEnviar.setBounds(10,300,200,50);
		btnEnviar.addActionListener(this);
		tela.add(btnEnviar);
		
		setSize(478,400);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", ServidorSMTP.getText());
        props.put("mail.smtp.socketFactory.port", Porta.getText());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", Porta.getText());

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Remetente.getText(), Senha.getText());
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("victor@virtualmake.net")); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(jtfDestinatario.getText());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(jtfAssunto.getText());//Assunto
            message.setText(jtaMensagem.getText());
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");
            JOptionPane.showMessageDialog(null, "Email Enviado com Sucesso");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro no Envio !");
            throw new RuntimeException(e);

        }
    
		
	}
	
	
      public static void main(String[] args) {   	
      	
		JavaMailApp app = new JavaMailApp();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
      }
      
      
      public void actionPerformed(ActionEvent e){
      		if(e.getSource() == btnEnviar){
      			
      		}
      }
      

}