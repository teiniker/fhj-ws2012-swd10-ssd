/**
 * 
 */
package at.fhj.swd.utils.sst;

import java.util.ArrayList;
import java.util.List;

import at.fhj.swd.domain.User;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractorResult {

    // Haui

    Boolean parsingSuccessfull;

    String errorMessage;

    List<User> users;

    
    /**
     * @return the parsingSuccessfull
     */
    public Boolean getParsingSuccessfull() {
	return parsingSuccessfull;
    }

    /**
     * @param parsingSuccessfull
     *            the parsingSuccessfull to set
     */
    public void setParsingSuccessfull(Boolean parsingSuccessfull) {
	this.parsingSuccessfull = parsingSuccessfull;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
	return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
	return users;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(List<User> users) {
	this.users = users;
    }

    
    /**
     * @param user
     *            the user to add
     */
    public void addUser(User user) {
	
	if ( this.users == null )
	    this.users = new ArrayList<User>();
	
	this.users.add(user);
	
    }    
    
    
    
}
