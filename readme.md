Create Patient:

cd patient/src/main/resources

curl -H "Content-Type: application/json" -d "@patient.json" -X POST http://localhost:8080/Patient


Search Patient:

curl http://localhost:8080/Patient?family=Mills |json_pp

