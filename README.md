# Client Server Programming Exercise
## Assumptions & Approach
* Fair Scheduler algorithm will traverse through the clients in the order it was added.
* Each client will get 4 seconds of time and the counter will be incremented accordingly.
* Any client can be manually disconnected at any point. If the scheduler is currently active with that client, during the next interval it will check the connection status. If client is disconnected, scheduler will move on to the next connected client.
* No provision is made to delete the client.
* Consider this code and POC with pretty much production quality.
* Spring boot is used for demonstration purposes only. The core components: FairScheduler, ClientService, and Client are essentially pojos that which be used anywhere.
* Integration and dev JUNITs are shown but not fully comprehensive.


## Installation
### Pre-Requisites to build code
* Maven 3.0+
* JDK 1.8+

### Setup
* Clone this project
* run 
``mvn clean install``
* run ``java -jar target/batonsys-0.0.1-SNAPSHOT.jar``
* Go to http://localhost:8080
* From there you can add client, disconnect client and see the output of the scheduler on your console.
