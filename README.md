# Database migration approaches and tools

## Flyway

Prerequirements.
Intead of h2 database postgres in container was used.

To run application with tests you need to run 
```bigquery
./gradlew clean test
```

docker-compose file will be executed before `test` method by default `dockerCompose.isRequiredBy(test)`
so make sure that no other containers with name `database` are executed at the same time

V3 version was splitted into several files 
* `V3__company.sql` is used to create company database
* `V3.1__employee.sql` for employees table and foreign keys

## Liquibase

Same approach is used with liquibase.
All sql files are located under `./src/main/resources/liquibase` folder.

![](.docs/Screenshot%202021-08-08%20at%2014.54.05.png)
![](.docs/Screenshot%202021-08-08%20at%2014.54.17.png)