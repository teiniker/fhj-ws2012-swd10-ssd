/**
 * 
 */
package at.fhj.swd.utils.sst;

import static org.junit.Assert.fail;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractorTest {

    private String goodString1WithOneLine = new String(
	    "firstname;lastname;department;location;username;password;email");

    private String goodString1WithThreeLines = new String(
	    "firstname;lastname;department;location;username;password;email"
		    + "\n"
		    + "firstname;lastname2;department;location;username;password;email2"
		    + "\n"
		    + "firstname;lastname3;department;my Location;username;password;email3");

    private CSVReader csvReaderOneLine;
    private CSVReader csvReaderThreeLines;

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getCSVReader(java.io.Reader)}
     * .
     */
    @Test
    public void testGetCSVReader() {

	/*
	 * Haui hier nun weitermachen
	 * 
	 * 
	 * 
	 * 
	 * this.csvReaderOneLine = SSTFileDataExtractor
	 * .getCSVReader(this.goodString1WithOneLine);
	 * 
	 * this.csvReaderThreeLines = SSTFileDataExtractor
	 * .getCSVReader(this.goodString1WithThreeLines);
	 */

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getListOfStringArraysFromCSVReader(au.com.bytecode.opencsv.CSVReader)}
     * .
     */
    @Test
    public void testGetListOfStringArraysFromCSVReader() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getParseResult(java.io.InputStream)}
     * .
     */
    @Test
    public void testGetParseResult() {
	fail("Not yet implemented");
    }

}
