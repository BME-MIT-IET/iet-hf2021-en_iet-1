# XSD To Owl test cases :
The program itself initially was testing the mappings of a incoming data from a static file input (XSD) to OWL ontology .

But there were no tests regarding the content of the file it self being null and if the file is and XSD file as it intends to be .

We first started with refactoring the code to reduce the code smells and define a function for all the redundant code and code duplication.

 Then we moved to implementing the test cases:



- checkFileType( ): We defined the functionality witch gets the extension of each and every one of the incoming files so by using that , the test case checks if our input file extension is the correct one by using assertion 



- testFileSafety():This test case will check the incoming file and its content , if we intend to convert the file with out any content , it will  fail this specific test , this has been done , using assertion.
