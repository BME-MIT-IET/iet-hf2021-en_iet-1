/**
 *
 */
package tr.com.srdc.ontmalizer.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.jena.ontology.OntModel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.srdc.ontmalizer.XML2OWLMapper;
import tr.com.srdc.ontmalizer.XSD2OWLMapper;
import org.junit.After;

/**
 * @author Mustafa
 *
 */
public class XML2OWLTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(XML2OWLTest.class);
    private final String CDA_XSD = "src/test/resources/CDA/CDA.xsd";
    private final String SAMPLE_FULL_CDA_INSTANCE_XML = "src/test/resources/CDA/SALUS-sample-full-CDA-instance.xml";
    private final String NULL_ROOT_XML = "src/test/resources/CDA/SALUS-sample-full-CDA-instance_null_root.xml";

    private final String TEST_XSD = "src/test/resources/test/test.xsd";
    private final String TEST_XML = "src/test/resources/test/test.xml";
    private final String TEST_INSTANCE_RDF = "src/test/resources/output/test-instance.rdf";

    private final String FIRST_PORT = "src/test/resources/CDA/first-prot";

    private final String SALUS_CIM_XSD = "src/test/resources/salus-common-xsd/salus-cim.xsd";
    private final String SALUS_CDA_INSTANCE_N3 = "src/test/resources/output/salus-cda-instance.n3";
    private final String SALUS_ELIGIBILITY_INSTANCE_N3 = "src/test/resources/output/salus-eligibility-instance.n3";
    private final String SALUS_ELIGIBILITY_INSTANCE_XML = "src/test/resources/salus-common-xsd/salus-eligibility-instance.xml";
    private final String SALUS_CIM_INSTANCE_N3 = "src/test/resources/output/salus-cim-instance.n3";
    private final String SALUS_CIM_INSTANCE_XML = "src/test/resources/salus-common-xsd/salus-cim-instance.xml";
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void convertXML2OWLTest() {
    	XSD2OWLMapper mapping = createMapper(CDA_XSD);

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(NULL_ROOT_XML), mapping);
        
        exception.expect(NullPointerException.class);         
        generator.convertXML2OWL();
    }

    @Test
    public void writeModelTest() {
    	XSD2OWLMapper mapping = createMapper(CDA_XSD);

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(SAMPLE_FULL_CDA_INSTANCE_XML), mapping);
        generator.convertXML2OWL();

        String outputPath = TEST_INSTANCE_RDF;
        String format = "N3";
    	boolean result = generator.writeModel(outputPath, format);
    	
    	assertEquals(result, false);
    }
    
    
    @Test
    public void createCDAOntologyInstance() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(CDA_XSD);

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(SAMPLE_FULL_CDA_INSTANCE_XML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        String outputPath = SALUS_CDA_INSTANCE_N3;
        String format = "N3";
    	generator.writeModel(outputPath, format);
    }
    
    @Test
    public void createFirstPrototypeCDAInstances() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(CDA_XSD);

        File folder = new File(FIRST_PORT);
        File[] files = folder.listFiles();
        if(files != null) {
	        for (File child : files) {
	            String inName = child.getName();
	            String outName = inName.substring(0, inName.lastIndexOf(".")) + "-cda.n3";
	
	            // This part converts XML instance to RDF data model.
	            XML2OWLMapper generator = new XML2OWLMapper(child, mapping);
	            generator.convertXML2OWL();
	
	            // This part prints the RDF data model to the specified file.
	            String outputPath = FIRST_PORT + outName;
	            String format = "N3";
	        	generator.writeModel(outputPath, format);
	        }
        }
    }

    @Test
    public void createSALUSCommonOntologyInstance() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(SALUS_CIM_XSD);

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(SALUS_CIM_INSTANCE_XML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        String outputPath = SALUS_CIM_INSTANCE_N3;
        String format = "N3";
    	generator.writeModel(outputPath, format);
    }

    @Test
    public void createSALUSEligibilityInstance() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(SALUS_CIM_XSD);

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(SALUS_ELIGIBILITY_INSTANCE_XML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        String outputPath = SALUS_ELIGIBILITY_INSTANCE_N3;
        String format = "N3";
    	generator.writeModel(outputPath, format);
    }

    @Test
    public void createTestOntologyInstance() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(TEST_XSD);
        
        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(TEST_XML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        String outputPath = TEST_INSTANCE_RDF;
        String format = "RDF/XML-ABBREV";
    	generator.writeModel(outputPath, format);
    }

    @Test
    public void testWriter() {

        // This part converts XML schema to OWL ontology.
    	XSD2OWLMapper mapping = createMapper(TEST_XSD);
        

        // This part converts XML instance to RDF data model.
        XML2OWLMapper generator = new XML2OWLMapper(new File(TEST_XML), mapping);
        generator.convertXML2OWL();

        // This part prints the RDF data model to the specified file.
        String outputPath = TEST_INSTANCE_RDF;
        String format = "RDF/XML-ABBREV"; 
    	generator.writeModel(outputPath, format);
    }
    
    private XSD2OWLMapper createMapper(final String mapper) {
        XSD2OWLMapper mapping = new XSD2OWLMapper(new File(mapper));
        mapping.setObjectPropPrefix("");
        mapping.setDataTypePropPrefix("");
        mapping.convertXSD2OWL();
        return mapping;
    }
}
