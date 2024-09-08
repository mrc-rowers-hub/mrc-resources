# Resources Service

WIP - Microservice to handle resources for MRC. 

## Running instructions 

### Database 
- Flyway used to spin up the DB based off of the scripts in db/migration 
- in db/localScripts, you can find scripts to populate the DB for local running 

## Notes
* sessions should start on the hour, and end at 59, or equivalently finish 1 minute prior to their end time 