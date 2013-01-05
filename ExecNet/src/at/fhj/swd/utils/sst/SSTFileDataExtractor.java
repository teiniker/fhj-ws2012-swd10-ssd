/**
 * Extracts the data out of the sst file, which is actually a comma separated (csv) file.
 */
package at.fhj.swd.utils.sst;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.fhj.swd.domain.User;
import at.fhj.swd.utils.UserMessage;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Extracts the data out of the sst file, which is actually a comma separated
 * (csv) file.
 * 
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractor {

    private static final Logger logger = Logger
	    .getLogger(SSTFileDataExtractor.class.getName());

    /**
     * Returns the new CSV Reader instance with the predefined options.
     * 
     * @param reader
     * 
     * @return The new CSV Reader instance with the predefined options.
     */
    static CSVReader getCSVReader(Reader reader) {

	return new CSVReader(reader, ';', '"', false);

    }

    /**
     * Returns the result of the csv parsing process as a List of String arrays.
     * 
     * @throws IOException
     * 
     */
    static List<String[]> getListOfStringArraysFromCSVReader(CSVReader csvReader)
	    throws IOException {

	return csvReader.readAll();

    }

    /**
     * Returns the result of the csv parsing process as an instance of the class
     * SSTFileDataExtractorResult.
     * 
     */
    public static SSTFileDataExtractorResult getParseResult(String stringToParse) {

	CSVReader _csvReader = getCSVReader(new StringReader(stringToParse));
	SSTFileDataExtractorResult _result = new SSTFileDataExtractorResult();

	User _user;

	List<String[]> _myDatas = null;

	_result.parsingSuccessfull = new Boolean(false);

	try {
	    _myDatas = SSTFileDataExtractor
		    .getListOfStringArraysFromCSVReader(_csvReader);
	} catch (IOException e) {

	    logger.fatal(e.getStackTrace());

	    _result.errorMessage = UserMessage
		    .getLocalizedMessage("admin_fatal_error");

	    return _result;

	}

	for (String[] line : _myDatas) {

	    if (line.length < 7 || line.length > 7) {

		// Delete all user objects that may already exist in the result
		// object.
		_result.setUsers(new ArrayList<User>());

		_result.errorMessage = UserMessage
			.getLocalizedMessage("admin_wrong_file_format");

		return _result;

	    }

	    _user = new User();

	    _user.setFirstname(line[0]);
	    _user.setLastname(line[1]);
	    _user.setDepartment(line[2]);
	    _user.setLocation(line[3]);
	    _user.setUsername(line[4]);
	    _user.setPassword(line[5]);
	    _user.setEmail(line[6]);

	    _result.addUser(_user);

	}

	_result.parsingSuccessfull = new Boolean(true);

	return _result;

    }
}
