# Coeus
A frontend for the TTC bot and the bot database.

## Building
To build Coeus, you need JDK Version 17.
```
git clone https://github.com/Stargirl-chan/coeus.git
cd coeus
./gradlew clean assemble
```
The resulting artifacts can be found under `./build/libs`

## Running
> **Note:** In order to run the frontend, a Postgresql Database is needed. See `./sql` subdirectory for a database script.

Make sure to provide an `application.properties` file in the same location where the jar file is located.
Example file:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=user
spring.datasource.password=pwd

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.thymeleaf.cache=false
```
Simply execute the jar file with Java
```
java -jar coeus-VERSION.jar
```
