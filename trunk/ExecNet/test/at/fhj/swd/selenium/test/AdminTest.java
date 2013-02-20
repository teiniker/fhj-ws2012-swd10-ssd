package at.fhj.swd.selenium.test;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;

/**
 * @author
 * 
 */
public class AdminTest extends AbstractTest {

    @Test
    public void test() {

	page.clickAdmin();

	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
