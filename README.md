## Background and context
The [Managed Libra records application (MLRA)](https://github.com/ministryofjustice/laa-mlra-application) is an LAA system that shares a database with the [means assessment administration tool (MAAT)](https://github.com/ministryofjustice/laa-maat-application). It links cases to their corresponding cases in the HMCTS Libra system. This is for the purposes of tracking cases through the course / Legal Aid system in a joined up way (i.e. updates of a person's status through the courts system is updated in LAA systems once a case is linked).

If an LAA caseworker searches for a case on the Libra system using MLRA and they are unable to find a corresponding case (this can happen for numerous reasons, for instance a Legal Aid application being submitted by a legal provider before the police/courts have added onto LIBRA), the case is marked by a caseworker as 'NOT ON LIBRA' and places onto a backlog queue (the 'not on libra' queue). Case workers will then research these cases at a later date to see if the corresponding case is now on Libra system.

This researching can take time as there is not a great way of prioritizing cases that may be on Libra. This is where the NOLASA application comes in.

## Not On Libra Auto-Search Application (NOLASA)
Not On Libra Auto-Search Application (NOLASA) is a microservice that reads cases that have been marked as 'not-on-libra' from the MLRA database once a day (8pm). It then auto-searches the HMCTS Libra system via the LAA's InfoX message broker service. The message protocol used is SOAP.

If NOLASA finds that there are results returned for a case it updates its status in MLRA to say 'RESULTS FOUND'. This means that caseworkers can filter 'not on libra' cases to ones that they know they will have results for. They can then prioritize their case searching and linking more effectively.

## NOLASA Springboot Microservice
The NOSALA microservice has been developed using Springboot framework with embedded Tomcat server. Tomcat is fully contained in the fat JAR file. Gradle packages executable JAR file which is deployed as Docker image on AWS ECS.

## Developer setup
### Pre-requisites
1. Docker
2. Java - required version specified in the file `.java-version`
3. SSH
4. Access to the MAAT development database
5. Java IDE - e.g. IntelliJ, eclipse, or atom

We're using [Gradle](https://gradle.org/) to build the application. This also includes plugins for generating IntelliJ configuration.


### 1. Clone the repository

```
git clone {this repo}
cd laa-nolasa
```

### 2. Build and run the app

You will need to build the artifacts for the source code, using `gradle`

```shell
./gradlew clean build
```

Information: The 'nolasa-0.1.0.jar' is located in:
```./build/libs```

### Verifying 1Password CLI install and permissions

To run the app locally, you will need to download the appropriate environment variables from the team
vault in 1Password. These environment variables are stored as a .env file, which docker-compose uses
when starting up the service. If you don't see the team vault, speak to your tech lead to get access.

To begin with, make sure that you have the 1Password CLI installed:

```sh
op --version
```

If the `op` command is not found, [follow the steps on the 1Password developer docs to get the CLI set-up](https://developer.1password.com/docs/cli/get-started/).

To check you have the correct permissions to see the team vault, run the following command to get
the documents stored in the team vault and verify that you see `EnvironmentVariables-nolasa-App` in
the output (optionally follow the command with `| grep EnvironmentVariables-nolasa-App` to filter down
to just the InfoX env vars file).

```sh
op document list
```

### Connecting to the MAAT database

To run the application, you will need to have the relevant database accessible on port 1521 locally.
To do this, [follow the steps to set-up the AWS CLI with the relevant environment's account on the modernisation platform](https://user-guide.modernisation-platform.service.justice.gov.uk/user-guide/accessing-ec2s.html)
and then use the `aws-ssm` command to open a connection to the database:

```sh
aws ssm start-session \
  --target <ec2-bastion-instance-id> \
  --document-name AWS-StartPortForwardingSessionToRemoteHost \
  --parameters '{"host":["<maat-db-endpoint>"],"portNumber":["1521"],"localPortNumber":["1521"]}' \
--profile <target-profile-defined-in-your-aws-config-file>
```

### Running the app

To run the app (against services in the dev environment) and pull down environment variables from
1Password automatically, run:

```sh
./start-local.sh
```

### 6. Configure your IDE

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