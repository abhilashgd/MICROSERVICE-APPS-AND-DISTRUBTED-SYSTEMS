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
            loadbalancer uses round robin to distribute requests. 
            create new instances of fraud from 
            edit configuration-->copy configuration.
            provide different server port  example (Program arguments --> --server.port=8088) and see application log for request processing

# OPEN FEIGN - CLIENTS MODULE

            https://spring.io/projects/spring-cloud-openfeign
            https://github.com/OpenFeign/feign

            Feign is a Java to HTTP client binder inspired by Retrofit, JAXRS-2.0, and WebSocket. Feign's first goal was reducing the complexity of binding Denominator uniformly to HTTP APIs regardless of ReSTfulness.

            How to include openfeign: https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/

            //inside main pom dependency management
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-openfeign</artifactId>
            </dependency>


            //Inside customer and fraud pom
             <dependency>
            <groupId>com.abhilashgd</groupId>
            <artifactId>clients</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>compile</scope>
            </dependency>

            //Customer Application - @EnableFeignClients

            Test using POSTMAN:
            POST: JSON RAW BODY:
                        {
                "firstName": "abhilash",
                "lastName": "gubbi",
                "email": "abhilashgd@test.com"
            }

            check details in DB: http://localhost:5050/browser/#
            in Fraud DB--> schemas-->Tables--> fraud_check_history
            run: SELECT * FROM public.fraud_check_history
                    ORDER BY id ASC 

            in Customer DB--> schemas-->Tables--> customer
            run: SELECT * FROM customer
                    ORDER BY id ASC 

#  Notification 

            Clients: notification client
            module: notification
            cutomer service class sends notification
            notification DB

# Distributed Tracing Using Sleuth and Zipkin
            Sleuth: Spring Cloud Sleuth provides Spring Boot auto-configuration for distributed tracing.
           Ref:  https://spring.io/projects/spring-cloud-sleuth
                 https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/getting-started.html#getting-started
                 

           slf4j MDC (mapped diagnostic context) helps us to enrich log data so that we can correlate data across multiple services
           Zipkin - compatible traces via HTTP. By default it sends them to a Zipkin collector service on localhost {port:9411}
            //Add below dependency for customer, eureka server, fraud and notification
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-sleuth</artifactId>
            </dependency>

 **SLEUTH IN ACTION** 
            Trace ID same across microservices but Span ID different for different microservice
            Log Details:
            customer log : INFO [customer,ba9a9bad1e60c5d2,ba9a9bad1e60c5d2]  //Trace ID: ba9a9bad1e60c5d2 Span ID: ba9a9bad1e60c5d
            fraud log:  INFO [fraud,ba9a9bad1e60c5d2,97968fdf8894c2b8] //Trace ID: ba9a9bad1e60c5d2 Span ID: 97968fdf8894c2b8
            notification log: INFO [notification,ba9a9bad1e60c5d2,afc5642cafe8377e] //Trace ID: ba9a9bad1e60c5d2 Span ID: afc5642cafe8377e

 **Zipkin In Action**
            Ref:  https://zipkin.io/
            Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. Features include both the collection and lookup of this data.

 **Zipkin Container**
            https://github.com/openzipkin-attic/docker-zipkin/blob/master/docker-compose.yml

            //inside docker-compose.yml
            zipkin:
                image: openzipkin/zipkin
                container_name: zipkin
                ports:
                - "9411:9411"
        
            % docker logs zipkin
            http://127.0.0.1:9411/
             //Add below dependency for customer, eureka server, fraud and notification
             <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-sleuth-zipkin</artifactId>
         </dependency>
        
        open: http://127.0.0.1:9411/zipkin/?lookback=15m&endTs=1646494271434&limit=10 and run query.
        

# //TODO:

# Implement API Gateway with Spring Cloud Gateway
        Dependency
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-gateway</artifactId>
            </dependency>

        Configuring  API Gateway

        cloud:
          gateway:
            routes:
                - id: customer
                uri: lb://CUSTOMER
                predicates:
                    - Path=/api/v1/customers/**



    POSTMAN Testing:
        localhost:8083/api/v1/customers
                {
            "firstName": "testname",
            "lastName": "test",
            "email": "testname@test.com"
        }

# implementMessage Queues with AMQP and RABBITMQ

    Simulate Slow Response
    
# Implement RabbitMQ container, message producer, listener
# package Microservices to runnable Jars
# package Jars to docker images
# implement Kubernetes
# Deploy Postgres RabbitMQ and Zipkin to k8s 
# Refactor Microservices for k8s
# Deploy Microservices to k8s
# Implement KAFKA
# implement Spring vault and secret management
# Implement reporting service

                
        





