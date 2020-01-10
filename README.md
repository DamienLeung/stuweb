# StuWeb

	This project is about a table system of logging all the student information, such as: ID, student name, phone number and address.
	
	This project is a demonstration of servlet and web java development. In this project there are two roles, the user and student, user is the privileged role which can do all the actions to student, and they can only access by validation from login servlet, student role only contains their own attributes, there is no action for student role.
	
	This project is about a table system of logging all the student information, such as: ID, student name, phone number and address.

## Structure

	This project can mainly divided into 5 parts, webapp directory, controller package , SQL connector, service, and POJO, and external part of pom.xml, which includes all the maven setting and repository.
	
	Webapp directory contains all the web page files and the corresponding files like JavaScript, jQuery files and bootstrap files.
	
	Controller package are the back-end control actions run by java. 
	
	SQL connector part is about SQL utilities and operators. Besides, the resources directory obtained the configuration file of mySQL.
	
	Service contains all the actions for user role.
	
	POJO contains all the constructors for this project.

### Controller

	In the controller package, there are three parts, student sub-package contains all the student actions for user to dealt with, for example, edit, add, delete and delete by checkbox, and the user sub-package contains login servlet to validate if the user is registered and privileged.
	
	Also both sub-packages are extended by the BaseServlet class as a filter to detect different actions provided by both sub-packages.

### SQL Connector

	As the connector for the database used by mySQL, customised JDBC utilities are needed, therefore util package contains the JDBCUtil class as the connector to read the resources files to get connection information and provide the connection, statement and result set as well as the close function for the attributes reminded.
	
	The dao package is for the data access object for service action to access to the database.

### Service

	As this information reminded before, because this project is a demonstration of servlet and web java development, therefore the service package contains only few functions as the dao package contains, the delete, edit, add etc., a simple service package.

### POJO

	The pojo package contains all the plain old java object for the constructor of student and user. 

### Web Pages

	This project consisted by three web pages, one table JavaScript and Bootstrap as css decoration. Those three web pages are index.jsp, studentTable.jsp and error.jsp, for JavaScript, tableJs.js is contained.
	
	For those web pages, bootstrap is embed. Index.jsp is as the welcome page for user to login to the student table page. StudentTable.jsp is the student table page to manage the information of student. The error.jsp page is the login failure page when user login with the wrong account or wrong password.
	
	tableJs.js is a JavaScript file to provide some dynamic actions for studentTable.jsp, for example, disable the button when required data is empty or the data did not meet the required format, enable the button when data is clean etc.