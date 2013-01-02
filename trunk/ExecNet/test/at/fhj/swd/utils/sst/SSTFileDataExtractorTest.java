/**
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
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractorTest {

    private static final String _goodString1WithOneLine = new String(
	    "firstname;lastname;department;location;username;password;email");

    private static final String _goodString1WithThreeLines = new String(
	    "firstname;lastname;department;location;username;password;email"
		    + "\n"
		    + "firstname;lastname2;department;location;username;password;email2"
		    + "\n"
		    + "firstname;lastname3;department;my Location;username;password;email3");

    private static final String _badString1 = new String(
	    "firstname;lastname;department;location;username;password;email"
		    + "\n"
		    + "firstname;lastname2;department;location;username;password;email2"
		    + "\n"
		    + "firstname;lastname3;department;my Location;username;password;email3;BadString");

    private static final String _badString2 = new String(
	    "firstname;lastname;department;location;username;password;email"
		    + "\n"
		    + "firstname;lastname2;location;username;password;email2"
		    + "\n"
		    + "firstname;lastname3;department;my Location;username;password;");

    @SuppressWarnings("unused")
    private CSVReader csvReaderOneLine;
    @SuppressWarnings("unused")
    private CSVReader csvReaderThreeLines;

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getCSVReader(java.io.Reader)}
     * .
     */
    @Test
    public void testGetCSVReader() {

	this.csvReaderOneLine = SSTFileDataExtractor
		.getCSVReader(new StringReader(this._goodString1WithOneLine));

	this.csvReaderThreeLines = SSTFileDataExtractor
		.getCSVReader(new StringReader(this._goodString1WithThreeLines));

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getListOfStringArraysFromCSVReader(au.com.bytecode.opencsv.CSVReader)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetListOfStringArraysFromCSVReader() throws IOException {

	CSVReader _reader1 = SSTFileDataExtractor
		.getCSVReader(new StringReader(this._goodString1WithOneLine));

	CSVReader _reader2 = SSTFileDataExtractor
		.getCSVReader(new StringReader(this._goodString1WithThreeLines));

	List<String[]> _myDatas = SSTFileDataExtractor
		.getListOfStringArraysFromCSVReader(_reader1);

	List<String[]> _myDatas2 = SSTFileDataExtractor
		.getListOfStringArraysFromCSVReader(_reader2);

	assertEquals(1, _myDatas.size());

	for (String[] line : _myDatas) {

	    assertEquals("firstname", line[0]);
	    assertEquals("lastname", line[1]);
	    assertEquals("department", line[2]);
	    assertEquals("location", line[3]);
	    assertEquals("username", line[4]);
	    assertEquals("password", line[5]);
	    assertEquals("email", line[6]);

	}

	assertEquals(3, _myDatas2.size());

	String[] line1 = _myDatas2.get(0);
	String[] line2 = _myDatas2.get(1);
	String[] line3 = _myDatas2.get(2);

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

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getParseResult(java.io.InputStream)}
     * .
     */
    @Test
    public void testGetParseResultThreeLines() {

	SSTFileDataExtractorResult _result = SSTFileDataExtractor
		.getParseResult(this._goodString1WithThreeLines);

	// The parsing has to be successful.
	assertEquals(true, _result.parsingSuccessfull.booleanValue());

	// We have exactly 3 new users.
	assertEquals(3, _result.users.size());

	assertEquals("firstname", _result.getUsers().get(0).getFirstname());
	assertEquals("lastname", _result.getUsers().get(0).getLastname());
	assertEquals("department", _result.getUsers().get(0).getDepartment());
	assertEquals("location", _result.getUsers().get(0).getLocation());
	assertEquals("username", _result.getUsers().get(0).getUsername());
	assertEquals("password", _result.getUsers().get(0).getPassword());
	assertEquals("email", _result.getUsers().get(0).getEmail());

	assertEquals("firstname", _result.getUsers().get(1).getFirstname());
	assertEquals("lastname2", _result.getUsers().get(1).getLastname());
	assertEquals("department", _result.getUsers().get(1).getDepartment());
	assertEquals("location", _result.getUsers().get(1).getLocation());
	assertEquals("username", _result.getUsers().get(1).getUsername());
	assertEquals("password", _result.getUsers().get(1).getPassword());
	assertEquals("email2", _result.getUsers().get(1).getEmail());

	assertEquals("firstname", _result.getUsers().get(2).getFirstname());
	assertEquals("lastname3", _result.getUsers().get(2).getLastname());
	assertEquals("department", _result.getUsers().get(2).getDepartment());
	assertEquals("my Location", _result.getUsers().get(2).getLocation());
	assertEquals("username", _result.getUsers().get(2).getUsername());
	assertEquals("password", _result.getUsers().get(2).getPassword());
	assertEquals("email3", _result.getUsers().get(2).getEmail());

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getParseResult(java.io.InputStream)}
     * .
     */
    @Test
    public void testGetParseResultWithBadString1() {

	SSTFileDataExtractorResult _result = SSTFileDataExtractor
		.getParseResult(this._badString1);

	// The parsing has not to be successful.
	assertEquals(false, _result.parsingSuccessfull.booleanValue());

	// We have exactly 0 new users.
	assertEquals(0, _result.users.size());

    }

    /**
     * Test method for
     * {@link at.fhj.swd.utils.sst.SSTFileDataExtractor#getParseResult(java.io.InputStream)}
     * .
     */
    @Test
    public void testGetParseResultWithBadString2() {

	SSTFileDataExtractorResult _result = SSTFileDataExtractor
		.getParseResult(this._badString2);

	// The parsing has not to be successful.
	assertEquals(false, _result.parsingSuccessfull.booleanValue());

	// We have exactly 0 new users.
	assertEquals(0, _result.users.size());

    }

}
