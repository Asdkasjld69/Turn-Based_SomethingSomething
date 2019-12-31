package security;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 发送邮件的工具类
 */
public class MailSender {
	static MailSender inst=new  MailSender();
	public static MailSender getInstance()
	{
		return inst;
	}
	
	private void useSSL(Properties props)
	{
	    MailSSLSocketFactory sf=null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);// 设置信任所有的主机
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	    
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.socketFactory", sf);
	}
	public  void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		
		final Properties mailsender=this.getSenderProperties();
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		// 设置邮件传输协议为SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// 设置SMTP服务器是否需要用户验证，需要验证设置为true
		props.setProperty("mail.smtp.auth", "true");
		
		if(mailsender.getProperty("mail.usessl", "false").equalsIgnoreCase("true"))
			this.useSSL(props);
		
		// 设置SMTP服务器地址
		props.setProperty("mail.host", mailsender.getProperty("mail.host"));
		
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				String user=mailsender.getProperty("mail.user");
				String passwd=mailsender.getProperty("mail.password");
				return new PasswordAuthentication(user, passwd);
			}
		};
		

		
		Session session = Session.getInstance(props, auth);
		
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		
		 // 设置发送者
		String sendermail=mailsender.getProperty("mail.from");
		message.setFrom(new InternetAddress(sendermail));
		
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
	
	public Properties getSenderProperties()
	{
		Properties props = new Properties();
		try {
			java.io.InputStream is=this.getClass().getResourceAsStream("/mail.properties");
		    props.load(is);
		}catch(Throwable t)
		{
			t.printStackTrace();
		}
		return props;
	}
}
