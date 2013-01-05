package at.fhj.swd.controller;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.log4j.Logger;

import at.fhj.swd.domain.User;
import at.fhj.swd.utils.EmailSender;

public class EmailSenderBean {

    static String _emailhost;
    static String _emailport;
    static String _emailuser;
    static String _emailpassword;
    static Properties _properties;
    static Authenticator _authenticator;
    static Logger logger;

    static {
        _emailhost = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("emailhost");
        _emailport = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("emailport");
        _emailuser = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("emailuser");
        _emailpassword = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("emailpassword");

        _properties = System.getProperties();
        _properties.put("mail.smtp.host", _emailhost);
        _properties.put("mail.smtp.port", _emailport);
        _properties.put("mail.smtp.auth", "true");
        _properties.put("mail.smtp.starttls.enable", "true");
        _authenticator = new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(_emailuser, _emailpassword);
            }
        };

        logger = Logger.getLogger(EmailSenderBean.class.getName());
    }

    public String Send() {
        String returnValue = "";

        FacesContext context = FacesContext.getCurrentInstance();

        User user = (User)context.getExternalContext().getSessionMap().get("user");
        EmailBean email = (EmailBean)context.getExternalContext().getSessionMap().get("emailBean");

        String bundlename = context.getApplication().getMessageBundle();
        ResourceBundle bundle = ResourceBundle.getBundle(bundlename, new Locale(user.getCulture()));

        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        try {
            EmailSender emailsender = new EmailSender(email.getRecipient(), email.getSubject(), email.getMessagetext(),
                _emailuser, user.getEmail(), user.getLastname(), _authenticator, _properties);
            emailsender.Send();

            fm.setDetail(bundle.getString("emailsendsuccess"));
            fm.setSummary(bundle.getString("emailsendsuccess"));

            returnValue = "OK";
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            fm.setDetail(bundle.getString("emailsenderror"));
            fm.setSummary(bundle.getString("emailsenderror"));

            returnValue = "Not OK";
        }

        context.addMessage(null, fm);
        return returnValue;
    }

}
