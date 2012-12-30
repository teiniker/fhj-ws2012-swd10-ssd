/**
 * Controller bean for the Java Server Faces page that acts as controller for it. 
 * 
 */
package at.fhj.swd.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileBean {

    // Haui todo

    public void handleFileUpload(FileUploadEvent event) {

	FacesMessage _msg = new FacesMessage("Succesful", event.getFile()
		.getFileName() + " is uploaded.");

	if (FacesContext.getCurrentInstance().getMessages("message_sst_file")
		.hasNext())
	    FacesContext.getCurrentInstance().getMessages("message_sst_file")
		    .remove();

	FacesContext.getCurrentInstance().addMessage("message_sst_file", _msg);

	// Haui
	System.out.println(event.getFile().getContents().toString());

	// event.getFile().getInputstream()

	// Haui hier später noch überlegen wegen der Anzeige der automatischen
	// Fehlermessages.
	// Jedoch: http://forum.primefaces.org/viewtopic.php?f=3&t=23853

    }

}
