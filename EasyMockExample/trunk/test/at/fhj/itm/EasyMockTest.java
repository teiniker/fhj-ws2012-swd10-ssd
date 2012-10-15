/**
 * This test cases show the usage of Mock Objects based on the EasyMock2 
 * framework.
 * 
 * EasyMock2 is a library that provides an easy way to use Mock Objects 
 * for given interfaces. 
 * (see http://www.easymock.org)
 */

package at.fhj.itm;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Ignore;
import org.junit.Test;

import at.fhj.itm.DAOException;
import at.fhj.itm.User;
import at.fhj.itm.UserDAO;


/**
 * Note that, usually, we don't test Mock objects directly. Instead, we use 
 * Mock Objects to fake objects used by an object under test.
 */
public class EasyMockTest
{
	@Test
	public void testInsert() throws DAOException
    {        
        // Configure mock object 
        UserDAO mock = createMock(UserDAO.class);
        mock.insert(new User(7, "Egon"));        
        replay(mock);

        // Run test case
        User p = new User(7, "Egon");
        mock.insert(p);
      
        // Verify mock interactions
        verify(mock);        
    }
    
	
	@Test
    public void testInsertException() throws DAOException
    {        
        // Configure mock object                
        UserDAO mock = createMock(UserDAO.class);
        mock.insert(new User(7, "Egon"));        
        expectLastCall().andThrow(new DAOException("Person already exists!"));        
        replay(mock);

        // Run test case
        try
        {
        	User p = new User(7, "Egon");
            mock.insert(p);
            fail();
        }
        catch(DAOException e)
        {
            // OK!
        }
        
        // Verify mock interactions        
        verify(mock);        
    }
    
	
	
	@Test
    public void testUpdate() throws DAOException
    {        
        // Configure mock object
        User p = new User(7, "Egon");        
        UserDAO mock = createMock(UserDAO.class);
        mock.update(p);
        expectLastCall().times(1);        
        replay(mock);
        
        // Run test case
        mock.update(p);
        mock.update(p);  // ERROR!!! (we call the update method twice) 
        
        // Verify mock interactions
        verify(mock);        
    }
    
	
	@Test
    public void testFindById() throws DAOException
    {        
        // Configure mock object 
        User p = new User(7, "Egon");
        int id = 13;
        UserDAO mock = createMock(UserDAO.class);
        expect(mock.findById(id)).andReturn(p); // also possible (not in this context): .andReturn((byte)17)
        replay(mock);

        // Run test case
        User x = mock.findById(13);
        assertEquals(7, x.getId());
        assertEquals("Egon", x.getName());

        // Verify mock interactions        
        verify(mock);        
    }
    

	@Test
    public void testDeleteException() throws DAOException
    {        
        // Configure mock object
        int id = 0;       
        UserDAO mock = createMock(UserDAO.class);
        mock.delete(id);        
        expectLastCall().andThrow(new DAOException("Wrong id!"));        
        replay(mock);

        // Run test case
        try
        {
            mock.delete(0);
            fail();
        }
        catch(DAOException e)
        {
            // OK!
        }
        
        // Verify mock interactions        
        verify(mock);        
    }
}
