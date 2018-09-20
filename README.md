## Back-ground and context
MLRA is an LAA system that is used to link cases to their corresponding cases in the HMCTS Libra system. This is for the purposes of tracking cases through the course / Legal Aid system in a joined up way (i.e. updates of a person's status through the courts system is updated in LAA systems once a case is linked).

If an LAA caseworker searches for a case on the Libra system using MLRA and they are unable to find a corresponding case (this can happen for numerous reasons, for instance a Legal Aid application being submitted by a legal provider before the police/courts have added onto LIBRA), the case is marked by a caseworker as 'NOT ON LIBRA' and places onto a backlog queue (the 'not on libra' queue). Case workers will then research these cases at a later date to see if the corresponding case is now on Libra system.

This researching can take time as there is not a great way of prioritizing cases that may be on Libra. This is where the Nolasa application comes in.

## NOLASA application
NOLASA (Not On Libra Auto-Search Application) is a micro-service that reads cases that have been marked as 'not-on-libra' from the MLRA database once a day (8pm). It then auto-searches the HMCTS Libra system via the LAA's Infox message broker service. The message protocol used is SOAP.

If NOLASA finds that there are results returned for a case it updates its status in MLRA to say 'RESULTS FOUND'. This means that caseworkers can filter 'not on libra' cases to ones that they know they will have results for. They can then prioritize their case searching and linking more effectively.

## Environment setup
TODO: Mian

## Building the app / war
```
git clone {this repo}
cd laa-nolasa
./gradlew clean build
```
The 'nolasa.war' is available at:
```./build/libs```

## Deployment
TODO: Mian