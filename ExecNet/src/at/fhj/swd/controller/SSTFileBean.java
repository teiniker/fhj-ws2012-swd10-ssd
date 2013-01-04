/**
 * Controller bean for the Java Server Faces page that acts as controller for it. 
 * 
 */
package at.fhj.swd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import at.fhj.swd.business.UserBO;
import at.fhj.swd.utils.sst.SSTFileDataExtractor;
import at.fhj.swd.utils.sst.SSTFileDataExtractorResult;

/**
 * Controller bean for the Java Server Faces page that acts as controller for
 * it.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileBean {

    private UserBO _userBO = null;

    private String textForUser = new String("");

    private int size = 0;

    private List<UploadedFile> files = new ArrayList<UploadedFile>();

    private static final Logger logger = Logger.getLogger(SSTFileBean.class
	    .getName());

    /**
     * Sets the uploaded files internally.
     * 
     * @param file
     *            the file to set
     */
    @SuppressWarnings("unused")
    private void setFile(List<UploadedFile> files) {
	this.files = files;
    }

    /**
     * Returns the uploaded files.
     * 
     * @return the file
     */
    public List<UploadedFile> getFiles() {
	return files;
    }

    /**
     * Adds one file to the internal list of uploaded files.
     * 
     * @param file
     *            the file to set
     */
    private void addFile(UploadedFile file) {
	this.files.add(file);
    }

    /**
     * Returns the text for the users that contains status information about the
     * upload and the import of users.
     * 
     * @return the textForUser
     * 
     */
    public String getTextForUser() {
	return textForUser;
    }

    /**
     * Sets the text for the users that contains status information about the
     * upload and the import of users.
     * 
     * @param textForUser
     *            the textForUser to set
     */
    private void setTextForUser(String textForUser) {
	this.textForUser = textForUser;
    }

    /**
     * Handels the upload of the file that contains user data, tries to import
     * the data and sets the feedback for the user.
     * 
     * @param event
     */
    public void uploadFile(FileUploadEvent event) {

	try {

	    if (this.files.size() > 0) {

		this.files.get(0).delete();
		this.textForUser = "";

	    }

	    this.files.clear();

	} catch (IOException e1) {
	    // Haui
	    logger.fatal(e1.getStackTrace());
	    showMessageToUser("Fataler Fehler aufgetreten. Bitte kontaktieren Sie den Support.");
	    return;
	}

	addFile(event.getUploadedFile());

	this.size = this.files.get(0).getData().length;

	SSTFileDataExtractorResult _result = new SSTFileDataExtractorResult();

	try {
	    _result = SSTFileDataExtractor.getParseResult(IOUtils
		    .toString(this.files.get(0).getInputStream()));
	} catch (IOException e) {
	    // Haui
	    logger.fatal(e.getStackTrace());
	    showMessageToUser("Fataler Fehler aufgetreten. Bitte kontaktieren Sie den Support.");
	    return;
	}

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

	showMessageToUser("Datei \""
		+ event.getUploadedFile().getName()
		+ "\""
		+ " wurde hochgeladen und alle sich darin befindlichen BenutzerInnen erfolgreich importiert.");

    }

    /**
     * Takes care the status message for the user will be shown to him.
     * 
     * @param messageString
     */
    private void showMessageToUser(String messageString) {

	setTextForUser(messageString);

    }

    /**
     * Returns the size of the uploaded file.
     * 
     * @return The size of the uploaded file as int.
     */
    public int getSize() {

	return this.size;

    }

}
