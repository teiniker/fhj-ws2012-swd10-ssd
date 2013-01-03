/**
 * Controller bean for the Java Server Faces page that acts as controller for it. 
 * 
 */
package at.fhj.swd.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.richfaces.event.FileUploadEvent;

import at.fhj.swd.business.UserBO;
import at.fhj.swd.utils.sst.SSTFileDataExtractor;
import at.fhj.swd.utils.sst.SSTFileDataExtractorResult;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileBean {

    private UserBO _userBO = null;

    private String textForUser = new String("");

    /**
     * @return the textForUser
     */
    public String getTextForUser() {
	return textForUser;
    }

    /**
     * @param textForUser
     *            the textForUser to set
     */
    private void setTextForUser(String textForUser) {
	this.textForUser = textForUser;
    }

    public void uploadFile(FileUploadEvent event) {

	// System.out.println(System.getProperty("user.dir"));
	SSTFileDataExtractorResult _result = new SSTFileDataExtractorResult();

	try {
	    _result = SSTFileDataExtractor.getParseResult(IOUtils
		    .toString(event.getUploadedFile().getInputStream()));
	} catch (IOException e) {
	    // Haui TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// Haui
	// System.out.println(event.getFile().getInputstream() );

	if (_result.getParsingSuccessfull() == false) {

	    // Haui
	    // Noch Textkürzel
	    showMessageToUser("Es konnte kein Import durchgeführt werden. Ursache: "
		    + _result.getErrorMessage());

	    return;

	}

	_userBO = new UserBO();

	if (_userBO.create(_result.getUsers()) == false) {

	    // Haui TODO
	    showMessageToUser("BenutzerInnen konnten nicht importiert werden.");
	    return;

	}

	// Haui hier später noch überlegen wegen der Anzeige der automatischen
	// Fehlermessages.
	// Jedoch: http://forum.primefaces.org/viewtopic.php?f=3&t=23853

	showMessageToUser("Datei \""
		+ event.getUploadedFile().getName()
		+ "\""
		+ " wurde hochgeladen und alle sich darin befindlichen BenutzerInnen erfolgreich importiert.");

	// Haui TODO
	// Noch die Seite nach dem importieren reloaden

    }

    /**
     * Haui TODO
     * 
     * @param messageString
     */
    private void showMessageToUser(String messageString) {

	FacesMessage _msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		"Information", messageString);

	if (FacesContext.getCurrentInstance().getMessages("message_sst_file")
		.hasNext())
	    FacesContext.getCurrentInstance().getMessages("message_sst_file")
		    .remove();

	FacesContext.getCurrentInstance().addMessage("message_sst_file", _msg);

	setTextForUser(messageString);

    }

    public void upload(FileUploadEvent event) {

	// UploadedFile _file = event.getUploadedFile();
	// _dbo.upload(_file, selectionItems);
    }

}
