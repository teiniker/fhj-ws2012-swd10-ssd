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


    @Test
    public void test_Set_GetEntry() {
        CommentBean bean = new CommentBean();
        bean.setEntry("This is a Entry");
    }

}
