package at.fhj.swd.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fhj.swd.utils.TestDataFactory;


public class CommentTest {

    @Test
    public void testGetEntry() {
        Comment comm = new Comment();

        comm.setEntry("Der Eintrag");
        assertEquals("Der Eintrag", comm.getEntry());
    }


    @Test
    public void testGetUser() {
        TestDataFactory fact = new TestDataFactory();
        Comment comm = new Comment();


        String TestU = "TestDepp";
        fact.createUser("TestDepp");
        User User = fact.createUser(TestU);
        comm.setUser(User);
        System.out.println(User);

        System.out.println(comm.getUser());
        // System.out.println(fact.toString());

        assertEquals(User, comm.getUser());


    }

    @Test
    public void testGetEntityName() {
        Comment comm = new Comment();
        System.out.println(comm.getEntityName());

        assertEquals("Comment", comm.getEntityName());


    }


}
