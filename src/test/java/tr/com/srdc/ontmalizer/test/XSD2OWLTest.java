/**
 *
 */
package tr.com.srdc.ontmalizer.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FilenameUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.srdc.ontmalizer.XSD2OWLMapper;

/**
 * @author Mustafa
 *
 */
public class XSD2OWLTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(XSD2OWLTest.class);

	private String xsdtest = "src/test/resources/test/test.xsd";
	private String saluscim = "src/test/resources/salus-common-xsd/salus-cim.xsd";
	private String CDA = "src/test/resources/CDA/CDA.xsd";
	private String POCD_MT000040 = "src/test/resources/CDA/POCD_MT000040.xsd";
	private String POCD_MT000040_Null = "src/test/resources/CDA/POCD_MT000040_Null.xsd";

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void createCDAOntology() {

		// This part converts XML schema to OWL ontology.
		XSD2OWLMapper mapping = createMapper(CDA);

		// This part prints the ontology to the specified file.
		FileOutputStream ont;
		try {
			File f = new File("src/test/resources/output/cda-ontology.n3");
			f.getParentFile().mkdirs();
			ont = new FileOutputStream(f);
			mapping.writeOntology(ont, "N3");
			ont.close();
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage());
		}

	}

	@Test
	public void createSALUSCommonOntology() {

		// This part converts XML schema to OWL ontology.
		XSD2OWLMapper mapping = createMapper(saluscim);

		// This part prints the ontology to the specified file.
		FileOutputStream ont;
		try {
			File f = new File("src/test/resources/output/salus-cim-ontology.n3");
			f.getParentFile().mkdirs();
			ont = new FileOutputStream(f);
			mapping.writeOntology(ont, "N3");
			ont.close();
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage());
		}

	}

	@Test
	public void createTestOntology() {

		// This part converts XML schema to OWL ontology.
		XSD2OWLMapper mapping = createMapper(POCD_MT000040);

		// This part prints the ontology to the specified file.
		FileOutputStream ont;
		try {
			File f = new File("src/test/resources/output/test.n3");
			f.getParentFile().mkdirs();
			ont = new FileOutputStream(f);
			mapping.writeOntology(ont, "N3");
			ont.close();
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage());
		}
	}

	@Test
	public void writerTest() {

		// This part converts XML schema to OWL ontology.
		XSD2OWLMapper mapping = createMapper(xsdtest);

		// This part prints the ontology to the specified file.
		try {
			File f = new File("src/test/resources/output/test.n3");
			f.getParentFile().mkdirs();
			Writer w = new FileWriter(f);
			mapping.writeOntology(w, "N3");
			w.close();
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage());
		}

	}

	@Test
	public void checkFileType() {
		String result = getExtension(xsdtest);
		assertEquals(result, "xsd");
	}

	
	@Test
	public void testFileSafety() {
		XSD2OWLMapper mapping = createMapper(POCD_MT000040);
		boolean result = mapping.parseXSD(new File("src/test/resources/CDA/POCD_MT000040.xsd"));
		assertEquals(result, true);
		boolean result2 = mapping.parseXSD(new File("src/test/resources/CDA/POCD_MT000040_Null.xsd"));
		assertEquals(result2, false);
	}

	// This part prints the ontology to the specified file.

	public String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	private XSD2OWLMapper createMapper(final String mapper) {
		XSD2OWLMapper mapping = new XSD2OWLMapper(new File(mapper));
		mapping.setObjectPropPrefix("");
		mapping.setDataTypePropPrefix("");
		mapping.convertXSD2OWL();
		return mapping;
	}
}
