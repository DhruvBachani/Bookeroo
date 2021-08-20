# RMIT SEPT 2021 Major Project

# Group 6 - Friday 3:30-5:30

## Members
* Dhruv Bachani     - s3808392
* Aditya Vadgama    - s3845898
* Linda Vu          - s3842580
* Harshita Kumar    - s3845841 
* David Nguyen      - s3843540

## Records

* Github repository: https://github.com/DhruvBachani/Bookeroo/
* Jira Board : https://xxxx
* Google Docs : https://drive.google.com/xxxx

	
## Code documentation - Release 0.1.0 - date
* feature 1
* feature 2
* feature 3
  

To run the application locally:
First ensure that you have  
An 
- IDE preferably IntelliJ to re-enact this readme as close as possible, 
- NodeJS (https://nodejs.org/en/download/)
- Configure JAVA_HOME onto your local machine - to check, go to command prompt and type echo %JAVA_HOME% and javac -version. 
- 	In the case of Windows 10, to do this, we go to Windows searchbar -> Advanced System Setting -> System Properties Window -> Environment Variables -> New System Variable
- 	From there you enter the name JAVA_HOME and the variable value is your JDK Directory.
- 	Once done, edit your System Path Variable to add new "%JAVA_HOME%\bin" and check again on the command prompt.

Once this is all downloaded:
1. Open the project on IntelliJ and enter in the terminal:
	- cd Backend/loginmicroservices
	- ./mvnw package
	- java -jar target/loginmicroservices-0.0.1.jar
2. In a second terminal, type:
	- cd FrontEnd/myfirstapp
	- npm install
	- npm start
