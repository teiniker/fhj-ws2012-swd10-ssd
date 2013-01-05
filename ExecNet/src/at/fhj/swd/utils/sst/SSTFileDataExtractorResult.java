/**
 * 
 * The class that contains all relevant information after parsing a file with
 * user data to import. 
 *
 */
package at.fhj.swd.utils.sst;

import java.util.ArrayList;
import java.util.List;

import at.fhj.swd.domain.User;

/**
 * The class that contains all relevant information after parsing a file with
 * user data to import.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractorResult {

    Boolean parsingSuccessfull = new Boolean(false);

    String errorMessage = "";

    List<User> users = new ArrayList<User>();

    /**
     * Returns if the last parsing process was successful.
     * 
     * @return the parsingSuccessfull
     */
    public Boolean getParsingSuccessfull() {
	return parsingSuccessfull;
    }

    /**
     * Sets internally if the parsing process was successful.
     * 
     * @param parsingSuccessfull
     *            the parsingSuccessfull to set
     */
    protected void setParsingSuccessfull(Boolean parsingSuccessfull) {
	this.parsingSuccessfull = parsingSuccessfull;
    }

    /**
     * Returns the error message if existing. Otherwise an empty string.
     * 
     * @return the errorMessage
     */
    public String getErrorMessage() {
	return errorMessage;
    }

    /**
     * Sets the error message.
     * 
     * @param errorMessage
     *            the errorMessage to set
     */
    protected void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }

    /**
     * Returns the list of users that comprise the result of the parsing
     * process.
     * 
     * @return the users
     */
    public List<User> getUsers() {
	return users;
    }

    /**
     * Sets the internal list of users.
     * 
     * @param users
     *            the users to set
     */
    protected void setUsers(List<User> users) {
	this.users = users;
    }

    /**
     * Adds one specific user to the internal list of users.
     * 
     * @param user
     *            the user to add
     */
    protected void addUser(User user) {

	if (this.users == null)
	    this.users = new ArrayList<User>();

	this.users.add(user);

    }

}
