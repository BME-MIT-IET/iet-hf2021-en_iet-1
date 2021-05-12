# Test Cases Documentation

### POJECT

- Product Name: Ontmalizer 

### PROJECT DESCRIPTION

Ontmalizer performs comprehensive transformations of XML Schemas (XSD) and XML data to RDF/OWL automatically. Since the free tools (specially the state of the art ones)
are not able to handle the complex sechemas and XML instances.
Only some commercial ones are able to do so. that is why this project was created, by using the sun's XSOM library for processing XML Schemas, Apache Xerces for processing XML data and Apache Jena for managing RDF data.


### PROGRAMMERS

- Kamyar Nazari
- Yazan Suleiman
- Amirali Shaban khamesh
- Hazmah Abdullah

## UNIT TEST SECTION

### UNIT TEST STRATEGY / EXTENT OF UNIT TESTING:

The program itself initially was testing the mappings of a incoming data from a static file input XML/XSD schema to OWL ontology .

But there were no tests regarding the content of the file it self being null and if the file is and XSD file as it intends to be .

Also, the new testcases added to the NamingUtilTest classdo the following:
 - check the behaviour of creating property names when the prefix and the propery name are long strings
 - check if the class modifies the inital of the property name before creating

We first started with refactoring the code to reduce the code smells and define a function for all the redundant code and code duplication. Then implemented the cases


## XSD2OWLTest

| \#  | TESTCASE | OBJECTIVE | INPUT | EXPECTED RESULTS | ACTUAL OUTCOME |
| --- | --------- |--------- | ----- | ---------------- | ----------------- |
| 1   | testFileSafety | Checking the input file's content          |  XSD File with a Null parent node       |    The test case shall check the file contents and return false in case of a file with a null node              |        The test case will fail (since the file content has a null node parent)  | 
| 2   | checkFileType | Checking the extension of the file         |  A file that has XSD extension       |    The test case shall check the extension and throw true in case of an XSD type file (failes otherwise)     |   The test case will pass(return true) since the file is an xsd file         |   

## XML2OWLTest

 \#  | TESTCASE| OBJECTIVE | INPUT | EXPECTED RESULTS | ACTUAL OUTCOME |
| --- | --------- | ----- | ---------------- | ----------------- | ----------|
|  1  | convertXML2OWLTest|Checking if an XML file has a Null root |  A XML file with a Null root is passed as the parameter to the converter function| Excepted for the function to throw NullPointerException has to be thrown|The NullPointerException has been thrown|
|  2  |writeModelTest     |Checking the extension of the output file|Performing the XML2OWL converter and checking if the output extension is required format|false has to be returned as the extension does not match|false has been returned |

## NamingUtilTest

| \# | TESTCASE |OBJECTIVE | INPUT | EXPECTED RESULTS | ACTUAL OUTCOME |
| --- | --------- | --------- | ----- | ---------------- | ----------------- |
| 1 | testCreatePropertyLongName | Checking the behaviour of creating long property name | Long prefix and property name strings | The returned value should equal the concatenation of the long strings | The class accepts long properties |
| 2 | testCreatePropertyFirstLetterModification | Checking if the method modifies the sub-strings initials | A prefix and a property name | The test case shall verify that creating the propery modifies the initial letter of the propery to follow the encoding convention | The class modifies the initial of the property name |