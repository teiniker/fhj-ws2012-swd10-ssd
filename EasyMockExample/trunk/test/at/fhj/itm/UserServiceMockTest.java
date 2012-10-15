package at.fhj.itm;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fhj.itm.DAOException;
import at.fhj.itm.ServiceException;
import at.fhj.itm.User;
import at.fhj.itm.UserDAOInterface;
import at.fhj.itm.UserService;


/**
 * These test cases use the PersonService object as an Object Under Test.
 * 
 * Note that the PersonService implementation uses the faked PersonDAO
 * implementation, thus, only the PersonService code is tested.
 */
public class UserServiceMockTest 
{   
    @Test
    public void testAddPerson() throws ServiceException, DAOException
    {
    	// Configure mock object
    	UserDAOInterface mock = createMock(UserDAOInterface.class);
        User p = new User(7, "Egon");        
        mock.insert(p);
        replay(mock);
        UserService service = new UserService(mock);     	

        // Run test case
    	User user = new User(7, "Egon");
        service.addUser(user);
        
        // Verify mock interactions 
        verify(mock);        
    }

    @Test
    public void testToCsv() throws ServiceException, DAOException
    {
    	// Configure mock object
    	UserDAOInterface mock = createMock(UserDAOInterface.class);
        User p = new User(7, "Egon");        
        expect(mock.findById(7)).andReturn(p);
        replay(mock);
        UserService service = new UserService(mock);     	
        
        // Run test case
        String csv = service.toCSV(7);
        assertEquals("7,Egon", csv);
        
        // Verify mock interactions
        verify(mock);
    }    
}
