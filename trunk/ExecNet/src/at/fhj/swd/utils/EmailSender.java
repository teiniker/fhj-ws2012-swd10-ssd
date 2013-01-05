package at.fhj.swd.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    String _recipient;
    String _subject;
    String _message;
    String _emailuser;
    String _sender;
    String _username;
    Authenticator _authenticator;
    Properties _properties;

    public EmailSender(String recipient, String subject, String message, String emailuser, String sender,
            String username, Authenticator authenticator, Properties properties) {
        this._recipient = recipient;
        this._subject = subject;
        this._message = message;
        this._emailuser = emailuser;
        this._sender = sender;
        this._username = username;
        this._authenticator = authenticator;
        this._properties = properties;
    }

    public void Send() throws AddressException, MessagingException {
        Session _session = Session.getDefaultInstance(_properties, _authenticator);
        Address[] address = new InternetAddress[1];
        address[0] = new InternetAddress(_sender);
        MimeMessage message = new MimeMessage(_session);
        message.setFrom(new InternetAddress(_emailuser));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(_recipient));
        message.setReplyTo(address);
        message.setSubject("ExecNet-Mail " + _username + ": " + _subject);
        message.setText(_message);
        Transport.send(message);
    }

}
