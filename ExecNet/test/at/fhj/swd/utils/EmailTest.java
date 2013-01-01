package at.fhj.swd.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

import org.junit.BeforeClass;
import org.junit.Test;

public class EmailTest {
	static Authenticator _authenticator;
	static Properties _properties;
	static String _recipient;
	static String _emailuser;
	static String _sender;

	@BeforeClass
	public static void setup() {
		_recipient = "bernhard.eibegger.itm10@fh-joanneum.at";
		_emailuser = "swdstudents@gmail.com";
		_sender = "eibegger@gmail.com";
		_properties = System.getProperties();
		_properties.put("mail.smtp.host", "smtp.gmail.com");
		_properties.put("mail.smtp.port", "587");
		_properties.put("mail.smtp.auth", "true");
		_properties.put("mail.smtp.starttls.enable", "true");
		_authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(_emailuser, "JSF1stsuper");
			}
		};
	}

	@Test(expected = AddressException.class)
	public void recipientAddress() throws AddressException, MessagingException {
		EmailSender emailsender = new EmailSender("", "Mail from Unittest",
				"Test-E-Mail", _emailuser, _sender, "swdstudent",
				_authenticator, _properties);
		emailsender.Send();
	}

	@Test(expected = AddressException.class)
	public void senderAddress() throws AddressException, MessagingException {
		EmailSender emailsender = new EmailSender(_recipient,
				"Mail from Unittest", "Test-E-Mail", "", _sender, "swdstudent",
				_authenticator, _properties);
		emailsender.Send();
	}

	@Test(expected = AddressException.class)
	public void replayAddress() throws AddressException, MessagingException {
		EmailSender emailsender = new EmailSender(_recipient,
				"Mail from Unittest", "Test-E-Mail", _emailuser, "",
				"swdstudent", _authenticator, _properties);
		emailsender.Send();
	}

	@Test
	public void send() throws AddressException, MessagingException {
		EmailSender emailsender = new EmailSender(_recipient,
				"Mail from Unittest", "Test-E-Mail sent from Unittest.",
				_emailuser, _sender, "swdstudent", _authenticator, _properties);
		emailsender.Send();
	}

}
