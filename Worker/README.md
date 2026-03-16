# WORKER SERVICE => Port : 8081

# Local DB Access 
     
       url: spring.datasource.url=jdbc:mysql://localhost:3306/construction

# Locally DB in use in Docker:
       url : spring.datasource.url=jdbc:mysql://localhost:3306/contructiondb_docker
       
#   SpringBoot Image Creation to Run in Docker & DB Run in Docker

       url: url: jdbc:mysql://mysql-docker:3306/construction 


# Loads which yml MySql local or docker :

* application.yml   *  application-dev.yml  * application-prod.yml

# Docker

1. create Docker Image
2. cmd : Before Run image give container name and tell which database use Local or Docker MySql. so container run with given File

        2.  docker run -d --name worker-container -p 8081:8081 -e SPRING_PROFILES_ACTIVE=prod worker-service

OR
 * **Docker Desktop** 

🔹 set Environment Variables when give container name, port etc in docker desktop 

    Add:

      Key	                        Value
      SPRING_PROFILES_ACTIVE	prod

       (or dev for dev profile)

#  DEV Docker container Run (local Database testing Auto Container Id created)

    docker run -p 8081:8081 -e SPRING_PROFILES_ACTIVE=dev worker-service


# 🚀 PROD Docker container (production Database)

    docker run -p 8081:8081 -e SPRING_PROFILES_ACTIVE=prod worker-service

-------------------------------------------------------------------------------------------------

6️⃣ Deploy Commands
 
      kubectl apply -f worker-service/k8s/configmap.yml
      kubectl apply -f worker-service/k8s/secret.yml
      kubectl apply -f worker-service/k8s/deployment.yml
      kubectl apply -f worker-service/k8s/service.yml


---------------------------------------------------------------------------------------------------

# Run Redis "Compulsory {500 Error}"

# Call Worker APIs :

1. create new Worker :

       post: http://localhost:8081/workers/create
       {
        "id": 101,
         "name": "Rahul",
         "skill": "Java",
         "phone": "9999999999",
         "status": "AVAILABLE"
          }
 
2. find by id :
   
       get: http://localhost:8081/workers/1
3. All workers :
  
        http://localhost:8081/workers/all

4. update by Id:
   
        patch : http://localhost:8081/workers/update/1
        pass field want to update
5. Delete :
      
        http://localhost:8081/workers/1

6. SiteId : List of workers work on site by ID
    
      Get:  http://localhost:8081/workers/site/101

7. get All Data By Name :

        http://localhost:8081/workers/name/ravi
       
          {
         "id": 101,
         "name": "Rahul",
         "skill": "Java",
          "phone": "9999999999",
         "status": "AVAILABLE",
         "workLogs": [
        { 
        "id": 1,
        "workerId": 101,
        "siteId": 10,
          "workDate": "2026-02-10",
        "startTime": "09:00:00",
        "endTime": "17:00:00",
        "hoursWorked": 8.0
        },
        {
        "id": 2,
        "workerId": 101,
        "siteId": 12,
        "workDate": "2026-02-11",
        "startTime": "10:00:00",
        "endTime": "18:00:00",
        "hoursWorked": 8.0
        }
       ]


# WorkLog - 2nd Entity store "worker data"  "/worklogs"

    1. id, 2.workerId; 3.siteId; 4.LocalDate workDate;
    5. LocalTime startTime; 6.LocalTime endTime; 7.Double hoursWorked

 1. save worker entry :

      http://localhost:8081/worklogs/create

2. worker data by worker Id:

   http://localhost:8081/worklogs/worker/101