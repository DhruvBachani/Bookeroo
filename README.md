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
* Jira Board: https://bookeroogroup6.atlassian.net/jira/software/projects/BG6/boards/1/backlog
* Google Docs: https://drive.google.com/drive/folders/1_-937tvipAGywIdPHBh7YxhbDMPe62FV?usp=sharing
	
## Code documentation - Release 0.1.0 - 21/09/2021
* Users can register and login with the neccessary data needed to run the book website.
* Admins can approve or decline publishers and shop owners.
* Books with the all neccessary data can now be added/deleted/updated.
* Sellers can now be added and post an ad to sell their books.
* Reviews can be added from the backend. 
  
To run the application locally:
First ensure that you have:
- An IDE preferably IntelliJ to re-enact this readme as close as possible, 
- NodeJS (https://nodejs.org/en/download/)
- Configure JAVA_HOME onto your local machine (Java 8 or 11) - to check, go to command prompt and type echo %JAVA_HOME% and javac -version. 
- 	In the case of Windows 10, to do this, we go to Windows searchbar -> Advanced System Setting -> System Properties Window -> Environment Variables -> New System Variable
- 	From there you enter the name JAVA_HOME and the variable value is your JDK Directory.
- 	Once done, edit your System Path Variable to add new "%JAVA_HOME%\bin" and check again on the command prompt.

Once this is all downloaded:
1. Open the project on IntelliJ and enter in the terminal, for each and every microservice:
	- cd Backend/[microservice]
	- ./mvnw package
	- java -jar target/[jar file e.g. loginmicroservices-0.0.1.jar]
2. In a second terminal, type:
	- cd FrontEnd/myfirstapp
	- npm install
	- npm start

Testing:
- All tests are conducted on the testing branch - which is exactly the same as the development/master branch (aside from the tests).
- We believe it's best to leave the testing to this branch to avoid having tests in our production code. 
- All microservices have unit tests that are configured to run at a './mvnw package' build (or './mvnw test' to just test the code).
- For frontend testings, all tests are configured to run at 'npm test' (might need to 'npm install again), once the test terminal appears, press 'a' to run all tests and then 'u' to update the snapshots.
- All tests are successful.

CircleCI:
- The CircleCI job builds are also only configured to the testing branch for the same reason above. All jobs were successful as seen from our testing branch as well as the screenshots from our documents.  

Setting up the Database using AWS RDS:
1. Go to https://aws.amazon.com/ and sign in to the console using an IAM user account that you have set up.

2. On the console, select RDS

3. Create a new Database
	For the creation wizard:
	- Select 'Standard Create'.
	- Select 'MySQL' as the Engine type.
	- For the settings, give your database an appropriate name and set up suitable credential settings.
	- Leave all other settings to default.
	- Create the Database.

4. With your new Database, edit the inbound rules which can be found in the security group of your Database to allow All traffic from Anywhere-IPv4

5. The Endpoint and Port of the Database will be given which can be used to access your database.

Deploying the application to AWS:
1. Open the project on IntelliJ, and in the terminal for each microservice:
	- cd BackEnd/[microservice]
	- ./mvnw package
This will create a .jar file in the 'target' folder of each microservice which will be used later on.

2. Then for the front-end:
	- cd FrontEnd/myfirstapp
	- npm install
	- npm run build
This will create a 'build' folder in the root directory which will be used later on.

3. Go to https://aws.amazon.com/ and sign in to the console using an IAM user account that you have set up.

4. On the console, select the Elastic Beanstalk service.

5. For each microservice:
	- Create a new environment.
	- Select 'Web server environment' and click select.
	- Enter an appropriate application name (Environmenent information can be optionally filled).
	- Select Java on Corretto 8 as your platform with the recommended version.
	- Upload the .jar file of the appropriate microservice.
After creating an application, AWS will provide a URL which will be used for API requests.

6. From the services drop-down menu, select the S3 service.

7. Create a new bucket.
	- Give your bucket a name for your front-end application.
	- Allow all public access and leave all other settings to default.
	- Create the bucket.

8. Upload all contents of the 'build' folder into the bucket.

9. Go to 'Properties' of the bucket.
	- Edit 'Static website hosting'.
	- Enable 'Static website hosting'.
	- Select 'Host a static website'.
	- Set both Index and Error document to 'index.html'.
	- Save Changes.

10. Go to 'Permissions' of the bucket.
	- Edit 'Bucket Policy'.
	- Put the following into your bucket policy, with the name of your bucket and save changes.
	```
	{
    		"Version": "2012-10-17",
    		"Statement": [
        		{
            			"Sid": "AddPerm",
            			"Effect": "Allow",
            			"Principal": "*",
            			"Action": "s3:GetObject",
            			"Resource": "arn:aws:s3:::NAME OF YOUR BUCKET HERE/*"
        		}
    	]
	}
	```
11. Going back to 'Properties' of the bucket should now show the URL of the endpoint of your website.
