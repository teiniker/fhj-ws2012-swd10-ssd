/**
 * Class to test the functionality of the SSTFileDataExtractorResult class. 
 */
package at.fhj.swd.utils.sst;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.domain.User;

/**
 * Class to test the functionality of the SSTFileDataExtractorResult class.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractorResultTest {

    private final SSTFileDataExtractorResult _goodResult = new SSTFileDataExtractorResult();

    private final SSTFileDataExtractorResult _badResult = new SSTFileDataExtractorResult();

    private static final String errorMessageString = new String(
	    "Bad event occured");

    /**
     * Constructs the two classes that will be tested. A good result and a bad
     * result.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

	testSetParsingSuccessfull();

	testSetErrorMessage();

	testSetUsers();

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#getParsingSuccessfull()}
     * .
     */
    @Test
    public void testGetParsingSuccessfull() {

	assertEquals(true, this._goodResult.getParsingSuccessfull()
		.booleanValue());

	assertEquals(false, this._badResult.getParsingSuccessfull()
		.booleanValue());

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#setParsingSuccessfull(java.lang.Boolean)}
     * .
     */
    @Test
    public void testSetParsingSuccessfull() {

	this._goodResult.setParsingSuccessfull(new Boolean(true));
	this._badResult.setParsingSuccessfull(new Boolean(false));

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#getErrorMessage()}
     * .
     */
    @SuppressWarnings("static-access")
    @Test
    public void testGetErrorMessage() {

	assertEquals(this.errorMessageString, this._badResult.getErrorMessage());

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#setErrorMessage(java.lang.String)}
     * .
     */
    @SuppressWarnings("static-access")
    @Test
    public void testSetErrorMessage() {

	this._badResult.setErrorMessage(this.errorMessageString);

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#getUsers()}.
     */
    @Test
    public void testGetUsers() {

	User _user = this._goodResult.users.get(0);

	assertEquals(1, this._goodResult.users.size());

	assertEquals("firstname", _user.getFirstname());

	assertEquals("lastname", _user.getLastname());

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#setUsers(java.util.List)}
     * .
     */
    @Test
    public void testSetUsers() {

	User _user = new User();

	List<User> _arrayListWithUser = new ArrayList<User>();

	_user.setFirstname("firstname");
	_user.setLastname("lastname");

	_arrayListWithUser.add(_user);

	this._goodResult.setUsers(_arrayListWithUser);

	testGetUsers();

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractorResult#addUser(at.fhj.swd.domain.User)}
     * .
     */
    @Test
    public void testAddUser() {

	User _user = new User();

	_user.setFirstname("firstname");
	_user.setLastname("lastname");

	this._goodResult.users.clear();

	this._goodResult.addUser(_user);

	testGetUsers();

    }

}
