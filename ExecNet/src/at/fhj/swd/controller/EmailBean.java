package at.fhj.swd.controller;

public class EmailBean {
	private String recipient;
	private String subject;
	private String messagetext;
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessagetext() {
		return messagetext;
	}
	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}
}
