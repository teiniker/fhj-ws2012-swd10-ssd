package at.fhj.swd.selenium.test;

import org.junit.Test;


import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.News;


public class NewsTest extends AbstractTest {

    @Test
    public void openNewsSiteAndAddNews() {
       
        News newsSite = page.clickNews();
        newsSite.insertNews("testnews", "test News text");
        
        
        
    }
    
}
