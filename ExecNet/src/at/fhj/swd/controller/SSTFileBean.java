/**
 * Controller bean for the Java Server Faces page that acts as controller for it. 
 * 
 */
package at.fhj.swd.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import at.fhj.swd.utils.sst.SSTFileDataExtractor;
import at.fhj.swd.utils.sst.SSTFileDataExtractorResult;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileBean {

    // Haui todo

    public void handleFileUpload(FileUploadEvent event) {

	SSTFileDataExtractorResult _result = SSTFileDataExtractor
		.getParseResult(event.getFile().getContents().toString());

	// Haui
	// System.out.println(_result.getErrorMessage());

	if (_result.getParsingSuccessfull() == false) {

	    // Haui
	    // Noch Textkürzel
	    showMessageToUser("Es konnte kein Import durchgeführt werden. Ursache: "
		    + _result.getErrorMessage());

	    return;

	}

	// Haui hier später noch überlegen wegen der Anzeige der automatischen
	// Fehlermessages.
	// Jedoch: http://forum.primefaces.org/viewtopic.php?f=3&t=23853

	showMessageToUser("\"" + event.getFile().getFileName() + "\""
		+ " is uploaded.");

    }

    /**
     * Haui TODO
     * 
     * @param messageString
     */
    private static void showMessageToUser(String messageString) {

	FacesMessage _msg = new FacesMessage("Information", messageString);

	if (FacesContext.getCurrentInstance().getMessages("message_sst_file")
		.hasNext())
	    FacesContext.getCurrentInstance().getMessages("message_sst_file")
		    .remove();

	FacesContext.getCurrentInstance().addMessage("message_sst_file", _msg);

    }

}
