/**
 * Verifys the functionality of the OpenCSV library that is used.
 * 
 */
package at.fhj.swd.utils.sst;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Verifys the functionality of the OpenCSV library that is used.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class TestOpenCSVLibrary {

    private String goodString1WithOneLine = new String(
	    "firstname;lastname;department;location;username;password;email");

    private String goodString1WithThreeLines = new String(
	    "firstname;lastname;department;location;username;password;email"
		    + "\n"
		    + "firstname;lastname2;department;location;username;password;email2"
		    + "\n"
		    + "firstname;lastname3;department;my Location;username;password;email3");

    /**
     * Haui TODO
     * 
     * @throws IOException
     */
    @Test
    public void doTestGoodInput1() throws IOException {

	CSVReader reader1 = SSTFileDataExtractor.getCSVReader(new StringReader(
		this.goodString1WithOneLine));

	List<String[]> myDatas = reader1.readAll();

	assertEquals(1, myDatas.size());

	for (String[] line : myDatas) {

	    assertEquals("firstname", line[0]);
	    assertEquals("lastname", line[1]);
	    assertEquals("department", line[2]);
	    assertEquals("location", line[3]);
	    assertEquals("username", line[4]);
	    assertEquals("password", line[5]);
	    assertEquals("email", line[6]);

	}

    }

    /**
     * Haui TODO
     * 
     * @throws IOException
     */
    @Test
    public void doTestGoodInputWithThreeLines() throws IOException {

	CSVReader reader1 = SSTFileDataExtractor.getCSVReader(new StringReader(
		this.goodString1WithThreeLines));

	List<String[]> myDatas = reader1.readAll();

	assertEquals(3, myDatas.size());

	String[] line1 = myDatas.get(0);
	String[] line2 = myDatas.get(1);
	String[] line3 = myDatas.get(2);

	assertEquals("firstname", line1[0]);
	assertEquals("lastname", line1[1]);
	assertEquals("department", line1[2]);
	assertEquals("location", line1[3]);
	assertEquals("username", line1[4]);
	assertEquals("password", line1[5]);
	assertEquals("email", line1[6]);

	assertEquals("firstname", line2[0]);
	assertEquals("lastname2", line2[1]);
	assertEquals("department", line2[2]);
	assertEquals("location", line2[3]);
	assertEquals("username", line2[4]);
	assertEquals("password", line2[5]);
	assertEquals("email2", line2[6]);

	assertEquals("firstname", line3[0]);
	assertEquals("lastname3", line3[1]);
	assertEquals("department", line3[2]);
	assertEquals("my Location", line3[3]);
	assertEquals("username", line3[4]);
	assertEquals("password", line3[5]);
	assertEquals("email3", line3[6]);

    }

}