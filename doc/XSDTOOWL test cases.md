## IDENTIFICATION INFORMATION SECTION

### POJECT

- **Product Name:** Ontmalizer 

### PROJECT DESCRIPTION

Ontmalizer performs comprehensive transformations of XML Schemas (XSD) and XML data to RDF/OWL automatically. Since the free tools (specially the state of the art ones)
are not able to handle the complex sechemas and XML instances.
Only some commercial ones are able to do so. that is why this project was created, by using the sun's XSOM library for processing XML Schemas, Apache Xerces for processing XML data and Apache Jena for managing RDF data.

### TEST PERSONNEL

- Amirali Shaban khamesh
- Hazmah Abdullah

### PROGRAMMERS

- Amirali Shaban khamesh
- Hazmah Abdullah

## UNIT TEST SECTION

### UNIT TEST STRATEGY / EXTENT OF UNIT TESTING:

The program itself initially was testing the mappings of a incoming data from a static file input XML/XSD schema to OWL ontology .

But there were no tests regarding the content of the file it self being null and if the file is and XSD file as it intends to be .

We first started with refactoring the code to reduce the code smells and define a function for all the redundant code and code duplication. Then implemented the cases


### UNIT TEST CASES

| \#  | OBJECTIVE | INPUT | EXPECTED RESULTS | TEST DELIVERABLES |
| --- | --------- | ----- | ---------------- | ----------------- |
| 1   | Checking the input file's content          |  XSD File with a Null parent node       |    The test case shall check the file contents and return false in case of a file with a null node              |               POCD_MT000040_Null.xsd   | 
| 2   | Checking the extension of the file         |  A file that has XSD extension       |    The test case shall check the extension and throw true in case of an XSD type file (failes otherwise)     |         test.xsd          |   