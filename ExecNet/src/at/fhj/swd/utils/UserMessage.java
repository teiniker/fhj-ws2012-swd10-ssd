/**
 * To retrieve localized text messages that are need by Javabeans.
 */
package at.fhj.swd.utils;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * To retrieve localized text messages that are need by Javabeans.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class UserMessage {

    /**
     * Retrieve a single localized text message that is needed in a Javabean or
     * elsewhere.
     * 
     * @param messageKey
     * 
     * @return The text for the user in his or her favored language.
     */
    public static String getLocalizedMessage(String messageKey) {

	FacesContext _context = FacesContext.getCurrentInstance();

	ResourceBundle _text = ResourceBundle.getBundle(
		"at.fhj.swd.i18n.messages", _context.getViewRoot().getLocale());

	return _text.getString(messageKey);

    }

}
