Change following matching to your MongoDB on application.properties

spring.data.mongodb.uri=mongodb://localhost:30001/

Start server:

mvn spring-boot:run

Create Patient:

cd patient/src/main/resources

curl -H "Content-Type: application/json" -d "@patient.json" -X POST http://localhost:8080/Patient


Search Patient:

curl http://localhost:8080/Patient?family=Mills |json_pp

