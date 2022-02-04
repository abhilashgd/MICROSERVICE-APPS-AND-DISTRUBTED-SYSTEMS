# MICROSERVICE-APPS-AND-DISTRUBTED-SYSTEMS


              Boot Strap with Gradle, 
              Spring Cloud, 
              Service Discovery with Eureka,
              Microservice Communication via HTTP,
              Open Feign, 
              Distributed Tacing - Sleuth, Zipkin, 
              API gateway with Spring Cloud Gateway,
              Message Queues - AMQP and RABBITMQ,
              RABBITMQ Implementation
              Packaging Microservices to runnable Jars
              Packaging Jars to Docker Images
              POSTGRES and PGADMIN,
              Docker 
              Kubernetes K8s
              Deploying Postgres RabbitMQ and Zipkin to k8s
              Refactoring Microservices for k8s
              Deploying Microservices to k8s
              Kafka
              
             
![Screen Shot 2022-02-04 at 11 38 34 PM](https://user-images.githubusercontent.com/21958756/152582614-288e6df1-1b0a-4b02-937e-9f686ca0a934.png)

# Boot Strap with Gradle

            % brew install cradle
            % gradle —version
            
            Reference: https://docs.gradle.org/current/samples/sample_building_java_applications.html
            
            create a project
            //cd to a empty project directory
            % grade init
            // Select type of project to generate:
            application
            //Select implementation language:
            java
            //Select build script DSL:
            Groovy
            //Select test framework:
            Junit Jupiter
            
            Project name (default: demo): com.abhilashgd
            Source package (default: demo):abhilashgdservices
            
            //TO run the application
            % ./gradlew run
            //To build the application
            %  ./gradlew build
            
            //To publish a build scan
            $ ./gradlew build --scan







