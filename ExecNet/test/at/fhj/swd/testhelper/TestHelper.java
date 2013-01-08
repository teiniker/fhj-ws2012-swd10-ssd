package at.fhj.swd.testhelper;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TestHelper {
    
	/**
	 * Shuts down derby 
	 */
  	 public static void ShutDownDerby() {
  		
  		 boolean gotSQLExc = false;  
  		  
  	         try {  
  	             DriverManager.getConnection("jdbc:derby:;shutdown=true");  
  	         } catch (SQLException se) {  
  	             gotSQLExc = true;  
  	         }  
  	
  	         if (!gotSQLExc) {
  	        	 //TODO: write to log file
  	             System.out.println("Database did not shut down normally");  
  	         } else {  
  	        	 //TODO: write to log file
  	             System.out.println("Database shut down normally");  
  	         }  
  		 
  	    
  	 }
	
}
