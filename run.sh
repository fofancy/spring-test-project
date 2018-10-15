( cd backend && cd config && sudo sh ./gradlew clean assemble && sudo docker build -t config .)
( cd backend && cd discovery && sudo sh ./mvnw clean package && sudo docker build -t discovery .)
( cd backend && cd gateway && sudo sh ./mvnw clean package && sudo docker build -t gateway .)
( cd backend && cd pictures && sudo sh ./gradlew clean assemble && sudo docker build -t pictures .)

#( cd devops && sudo docker-compose -f docker-compose.yml up)
