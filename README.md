# MICROSERVICE-APPS-AND-DISTRIBUTED-SYSTEMS


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

            % brew install gradle
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

# DOCKER 

            % docker compose up -d  //-d for detach 
            % docker compose ps

# PGADMIN
         
            //localhost:5050
            pgadmin master password: password
            //add a new server
            name:abhilashgd
            connection host name: postgres
                        username: abhilashgd
                        password: password

# Customer Microservice and Fraud check 

                CUSTOMER MICRO SERVICE 
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                </dependency>

            //annotate Customer application class
            @EnableEurekaClient

            //application.yml
            eureka:
                client:
                  service-url:
                    defaultZOne: http://localhost:8761/eureka

            //RUN customer application and see if its getting reflected in eureka server (http://localhost:8761/)
            Configuration --> edit configuration-->duplicate customer application-->name it as customer application2-->
                environment-->program argument--> --server.port=8085-->apply
            //RUN customer application  2 and see if availability zones=2, in eureka server (http://localhost:8761/)
            deleting second instance --> Configuration --> edit configuration--> delete

            change localhost:8081 to FRAUD in customer service
            in application.yml - change ddl-auto to create-drop (only in dev not in production)
            Features same as CUSTOMER MICRO SERVICE and FRAUD CHECK APP in my repo.

            //FRAUD MICROSERVICE

            //Dependency
             <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>

             //annotate Customer application class
            @EnableEurekaClient

            //application.yml
            eureka:
                client:
                  service-url:
                    defaultZOne: http://localhost:8761/eureka
              //RUN fraud application and see if its getting reflected in eureka server (http://localhost:876

# EUREKA SERVER

             - Eureka clients
            - Eureka server 
                    - clients register with server and request for service location
                    - must be up and running all the time
                    - kebernetes removes the bottleneck of eureka server

            https://spring.io/-->Projects-->Spring Cloud

            Spring Cloud provides tools for developers to quickly build some of the common 
            patterns in distributed systems (e.g. configuration management, service discovery, 
            circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, 
            leadership election, distributed sessions, cluster state). Coordination of distributed systems
            leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services 
            and applications that implement those patterns. They will work well in any distributed environment, 
            including the developer’s own laptop, bare metal data centres, and managed platforms such as Cloud Foundry.

            //Dependency
            <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring.cloud-version}</version>
            <type>pom</type>
            <scope>import</scope>
            </dependency>

            //mention version
            <spring.cloud-version>2020.0.3</spring.cloud-version>

            //Create Eureka Server Module,
            //Add Eureka Server Dependency
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
            </dependency>

            //Eureka Server class
            annotate it with
            @SpringBootApplication
            @EnableEurekaServer
            
            //Add Application.yaml in eureka-server module
            
            //Run Eureka Server
            BROWSER--> http://localhost:8761/

# LOAD BALANCING

            customer config class bean (RestTemplate) must be annotated with 
            @LoadBalanced

            

                
        





