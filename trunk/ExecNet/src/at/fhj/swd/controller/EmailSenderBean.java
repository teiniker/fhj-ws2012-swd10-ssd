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
	
	static{
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
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(_emailuser, _emailpassword);
			}
		};
		
		logger = Logger.getLogger(EmailSenderBean.class.getName());
	}
	
	public String Send(String recipient, String subject, String message){
		String ret = "Not OK";
		FacesContext context;
		String bundlename;
		ResourceBundle bundle;
		User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		
		try{			
			EmailSender emailsender = new EmailSender(recipient, subject, message, _emailuser, user.getEmail(), user.getLastname(), _authenticator, _properties);
			emailsender.Send();
			
			context = FacesContext.getCurrentInstance();
			bundlename = context.getApplication().getMessageBundle();
			bundle = ResourceBundle.getBundle(bundlename, new Locale(user.getCulture()));
			FacesMessage fm = new FacesMessage();
			fm.setDetail(bundle.getString("emailsendsuccess"));
			fm.setSummary(bundle.getString("emailsendsuccess"));
			fm.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, fm);
			ret = "OK";
		}
		catch(Exception ex){
			logger.error(ex.getMessage());
			
			context = FacesContext.getCurrentInstance();
			bundlename = context.getApplication().getMessageBundle();
			bundle = ResourceBundle.getBundle(bundlename, new Locale(user.getCulture()));
			FacesMessage fm = new FacesMessage();
			fm.setDetail(bundle.getString("emailsenderror"));
			fm.setSummary(bundle.getString("emailsenderror"));
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, fm);			
		}
		return ret;
	}
}
