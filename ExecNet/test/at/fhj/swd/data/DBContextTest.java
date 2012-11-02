package at.fhj.swd.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.domain.User;
import at.fhj.swd.utils.TestDataFactory;

public class DBContextTest {

    private IDataContext<User> _context;

    @Before
    public void setup() {
        _context = new DBContext<User>();
    }

    @Test
    public void testReadOneByQuery() throws Exception {
        TestDataFactory _f = new TestDataFactory();

        User _u1 = _f.createUser("hugo");
        _context.create(_u1);

        User _u = _context.readOneByQuery("i._username='hugo'", User.class);

        assertNotNull(_u);
    }

}
