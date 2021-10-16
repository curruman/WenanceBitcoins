# MICROSERVICE MESSAGES CRIPTO CURRENCY

## Project Documentation

See path in the project:
 
`docs/developer_guide/developer_guide.md`

And, when running the service you can check the following link to see the specifications of the endpoints used in the following link

http://localhost:8080/swagger-ui.html#/

## Clone Project
1. Clone repository 

	`git clone https://github.com/curruman/WenanceBitcoins.git`

2. Change for branch **master** to **develop** (or any other)

	`cd <root_repo>`
	`git checkout develop`

##  Deploy
Description of the procedures for generating the * price * project packages

### 1. Update Bitcoin
Update the folder with the sources of the git branch of interest (* develop * or * release *), directly from the git repository.
In the * workspace * folder, execute the commands:

    git pull

> ** NOTE: ** if you don't already have the project in your workspace, perform the procedure [Project Clone] (# clone-project)

### 2. Execution of Build
Before performing this step, make sure:

- Setting the environment variable * SPRING_CONFIG_LOCATION *
  
	`SPRING_CONFIG_LOCATION = classpath:/,file:<X:/workspace_dir>/wenance-bitcoins-message/config/`

- Parameterization of VM in project:
	
	`-DprojectName=<sub_project_name>`

Run in the root folder of the fireoperation project, the task maven

				mvn clean install
		
> **OBS.:** 
> *this script will perform the following tasks in the build process:
> * build
> * tests
> * copy
>
However, it is not uncommon to break tests during the build, in which case it will be necessary to:
** a) ** identify the project (s) whose tests failed
** b) ** correct the reason (s) for each failure
** c) ** run the project tests again the packages
> * the deploy packages for each project will be automatically copied to the subfolder **. \ build \  ** in 'zip' format

### 3. Generation Report of Build Sonarqube
This step includes:
* generate the report
* inspect the report for ** bugs ** or ** vulnerabilities **
* fix sources to eliminate * bugs * and * vulnerabilities *

The generator of migration of flyway is generated by executing:

				mvn flyway:clean
				mvn flyway:migrate

### 4. Installing Build Packages (* .zip) for the Server
Copy the packages generated in ** step 2 ** to the destination folder ** / srv / capture / fireoperation ** on the desired server. After copying, extract all packages.

The following unzip command can help:

				find . -iname \*.zip -exec 7za x {} \;

 > **OBS.:** The destination folder must have the following structure:
  > ```  
  >  \srv\captura\currency
  >		|   currency.sh
  >		+---config
  >		|   application.properties
  >		+---log
  >		|    logback-spring.xml
  >		+---project
  >```
  > *Where*:
  > 
 > **a)** the 'project *' folder refer to the project folder, which will appear after extracting the 'zip' packages;
 > 
 > **b)** the following files can be found in the git project:
 > * ./config/application.properties
 > * ./logs/logback-spring.xml
	 		When the 'application.properties' file is copied for the first time,
			edit for the configuration:
			   `wenance-bitcoins.logging.path=../../logs`

### 5. Running Price Project
For each service subfolder with the prefix * price * in the name, the `currency.sh` script will create a background process (* nohup *) on the server.

### 6. Service Execution Verification
To check if project were started correctly for this, you can use the command 'ps' which lists the services in 'java' that are running:
		ps -fC java
			
or:

		tail -f log/<filen>.log -n 2000000


### 7. Dealing with `Merge Request`

The capture team works with `Merge Request` and thus facilitates ** Code Review **.
However, this technique creates many branches in the project. So it is necessary to delete the local branches from time to time.
For this we use the following commands:

#### Remove branches that are not on the server
> git remote prune origin

#### Remove branches from local

> git branch -vv | grep 'origin/.*: gone]' | awk '{print $1}' | xargs git branch -d
