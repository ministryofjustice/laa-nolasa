## Background and context
The [Managed Libra records application (MLRA)](https://github.com/ministryofjustice/laa-mlra-application) is an LAA system that shares a database with the [means assessment administration tool (MAAT)](https://github.com/ministryofjustice/laa-maat-application). It links cases to their corresponding cases in the HMCTS Libra system. This is for the purposes of tracking cases through the course / Legal Aid system in a joined up way (i.e. updates of a person's status through the courts system is updated in LAA systems once a case is linked).

If an LAA caseworker searches for a case on the Libra system using MLRA and they are unable to find a corresponding case (this can happen for numerous reasons, for instance a Legal Aid application being submitted by a legal provider before the police/courts have added onto LIBRA), the case is marked by a caseworker as 'NOT ON LIBRA' and places onto a backlog queue (the 'not on libra' queue). Case workers will then research these cases at a later date to see if the corresponding case is now on Libra system.

This researching can take time as there is not a great way of prioritizing cases that may be on Libra. This is where the Nolasa application comes in.

## Not On Libra Auto-Search Application (NOLASA)
Not On Libra Auto-Search Application (NOLASA) is a micro-service that reads cases that have been marked as 'not-on-libra' from the MLRA database once a day (8pm). It then auto-searches the HMCTS Libra system via the LAA's Infox message broker service. The message protocol used is SOAP.

If NOLASA finds that there are results returned for a case it updates its status in MLRA to say 'RESULTS FOUND'. This means that caseworkers can filter 'not on libra' cases to ones that they know they will have results for. They can then prioritize their case searching and linking more effectively.

## NOLASA Springboot Micro-service
The NOSALA micro-service has been developed using Springboot framework with enbedded Tomcat server. Tomcat is fully contained in the fat JAR file. Gradle packages executable JAR file which is deployed as Docker image on AWS ECS.

## Developer setup
### Pre-requisites
1. Docker
1. SSH
1. Access to the MAAT development database
1. An editor/IDE of some sort for example IntelliJ, eclipse, or atom.

We're using [Gradle](https://gradle.org/) to build the application. This also includes plugins for generating IntelliJ configuration.


### 1. Clone the repository

```
git clone {this repo}
cd laa-nolasa
```

### 2. Set up an SSH tunnel to access the database

Database and InfoX endpoints need to be up and running before the application runs. This step is not required if you are running in an AWS workspace.

Database:
You will need to have the relevant database accessible on port 1521 locally. This can be provided by an SSH tunnel to an RDS instance in AWS. Here is the command to tunnel to Dev (add your user Bastion user name):

```sh
ssh -L 1521:rds.maat.aws.dev.legalservices.gov.uk:1521 <username>@35.176.251.101 -i ~/.ssh/id_rsa
```

### 3. InfoX connection
Nolasa requires connection to InfoX to search against Libra. The simplest way is to run InfoX stub locally by following the instructions from https://github.com/ministryofjustice/laa-infoX-application.
The application needs an environment variable to be provided when the container is run so the InfoX connection works correctly

The LIBRA_ENDPOINTURI environment variable has been assigned to http://host.docker.internal:8080/infoX/gateway, but if you want to connect to a different endpoint you can set the environment variable as follows:

```sh
-e LIBRA_ENDPOINTURI=http://172.16.3.131:8550/infoX/gateway
```

### 4. Build and run the app

You will need to build the artifacts for the source code, using `gradle`

```
./gradlew clean build
```

Information: The 'nolasa-0.1.0.jar' is located in:
```./build/libs```


The apps should then startup cleanly if you run

```sh
docker-compose build
docker-compose up app
```

This application does not have any user interface, so nothing would be available on the corresponding web page http://localhost:8081.

Environment variables are specified for DEV environment. It is also possible to override them before running docker-compose. If you want to connect to a different environment you can override them with the following runtime arguments:

```sh
docker-compose run -e DATASOURCE_URL=jdbc:oracle:thin:@host.docker.internal:1521:maatdb -e DATASOURCE_USERNAME=mla -e DATASOURCE_PASSWORD=dietc0ke -e LIBRA_ENDPOINTURI=http://host.docker.internal:8080/infoX/gateway app
```

### 5. Configure your IDE

Run `./gradlew tasks` to see more details.

Eclipse and IntelliJ both support Gradle projects. You should be able to define a new project in either IDE, and import this codebase.

That is the recommended approach.

Alternatively, you can use Gradle to generate configuration for your preferred IDE.

* `./gradlew eclipse` will generate the Eclipse meta-data project files
* `./gradlew idea` will generate the IntelliJ meta-data project files

We do not check these files into version control. Gradle is our repeatable build process; not an IDE.

We use [Lombok](https://projectlombok.org/) for code generation, so you need to install a plugin for whichever IDE
you are using.

## Additional documentation
- [Metrics](./doc/metrics.md)