/**
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

	FacesContext.getCurrentInstance().addMessage(null, _msg);

    }

}
