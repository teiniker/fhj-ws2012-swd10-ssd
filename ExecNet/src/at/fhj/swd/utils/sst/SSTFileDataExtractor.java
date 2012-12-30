/**
 * 
 */
package at.fhj.swd.utils.sst;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Michael Hausegger, CAFK
 * 
 */
public class SSTFileDataExtractor {

    // Haui noch überlegen ob du diese Klasse wirklich brauchst.

    static CSVReader getCSVReader(Reader reader) { // Haui hier sollte doch ein
						   // InputStram übergeben
						   // werden.
						   // Oder nicht?

	return new CSVReader(reader, ';', '"', false);

    }

    /**
     * Haui TODO
     * 
     * @throws IOException
     * 
     */
    static List<String[]> getListOfStringArraysFromCSVReader(CSVReader csvReader)
	    throws IOException {

	return csvReader.readAll();

    }

    /**
     * Haui TODO
     * 
     */
    public static SSTFileDataExtractorResult getParseResult(
	    InputStream inputStream) {

	CSVReader _csvReader = getCSVReader(new InputStreamReader(inputStream));

	// Haui
	return new SSTFileDataExtractorResult();

    }

}
