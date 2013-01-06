package at.fhj.swd.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CommentBeanTest {

    @Test
    public void test_Set_GetInputEntry() {
        CommentBean bean = new CommentBean();
        bean.setInputEntry("This is a Entry");
        assertEquals("This is a Entry", bean.getInputEntry());
    }

    // @Test
    // public void testCommentBeanIsNull() {
    //
    //
    // System.out.print("Not yet implemented");
    // fail("Not yet implemented");
    // }

    // @Test
    // public void testCommentBean() {
    // System.out.print("Not yet implemented");
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testCommentsOrPostEmpty() {
    // fail("Not yet implemented");
    // }


    // @Test
    // public void testGetcomments() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testAdd() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testGetEntry() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testSetEntry() {
    // fail("Not yet implemented");
    // }

}
