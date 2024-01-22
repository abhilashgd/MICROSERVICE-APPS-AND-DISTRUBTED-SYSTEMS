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
            % gradle init
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
    RABBITMQ - https://www.rabbitmq.com/ 
    https://www.rabbitmq.com/tutorials/amqp-concepts.html 
    AMAZON SQS - https://aws.amazon.com/sqs/ 

    RabbitMQ config in docker-compose.yml

    rabbitmq:
    image: rabbitmq:3.9.11-management-alpine
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"

    //in command prompt
      % docker compose up -d

    //in any browser 
    http://localhost:15672/ 
    username - guest
    password - guest

# Implement RabbitMQ container, message producer, listener

    *AMQP* -refer AMQP package and RabbitMQConfig
    //inside notification and customer pom.xml - add amqp dependency
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
         <dependency>
            <groupId>com.abhilashgd</groupId>
            <artifactId>amqp</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    //Notification Queue Configuration
    //Defining exchange and binding
    //message producer
    //publishing messages

    check- http://localhost:15672/#/queues notification.ques must be present

    AMQP protocol - delegate=amqp://guest@127.0.0.1:5672/

    Note: notifcation message producer is just for testing purpose

    //Configuring for Customer
    add scanBasePackages in customer application and publish the message through
    customer service rabbitMQMessageProducer.publish

    //Testing
    POSTMAN:
    POST: localhost:8080/api/v1/customers
                {
                    "firstName": "abhilash",
                    "lastName": "gd",
                    "email": "abhilashgd@test.com"
                }

    in http://localhost:15672/#/queues/%2F/notification.queue we shoudl see qued up message when we check in get message

# KAFKA - https://kafka.apache.org/

       - High Throughtput    
       - Scalable
       - permanent storage
       - high availability

# package Microservices to runnable Jars

        https://maven.apache.org/plugins/maven-compiler-plugin/

    //inside main pom.xml
     <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>17</source>
          <target>17</target>
        </configuration>
      </plugin>
    </plugins>

        ``packaging whole application

        https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#packaging

        add below execution for maven plugin. // without this we wont be able to run the application
                <executions>
                        <execution>
                            <goals>
                                <goal>repackage</goal>
                            </goals>
                        </execution>
                    </executions>

        // in ev ery microservice, add below things and remove properties

        <packaging>jar</packaging>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
                </plugins>
            </build>

        % cd
        % cd  .m2/repository
        % cd com
        $ cd /Users/abhilashgd/.m2/repository/com/abhilashgd

        maven-->lifecycle--> install (install the package into local repository, for use as a dependency in other projects locally)

        check all jars are installed inside example 
        /Users/abhilashgd/.m2/repository/com/abhilashgd

        [INFO] abhilashgdservices ................................. SUCCESS [  0.112 s]
        [INFO] amqp ............................................... SUCCESS [  1.234 s]
        [INFO] clients ............................................ SUCCESS [  0.311 s]
        [INFO] customer ........................................... SUCCESS [  1.284 s]
        [INFO] fraud .............................................. SUCCESS [  0.588 s]
        [INFO] eureka-server ...................................... SUCCESS [  0.619 s]
        [INFO] notification ....................................... SUCCESS [  0.717 s]
        [INFO] apigw .............................................. SUCCESS [  0.605 s]
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

# Running all JARS
inside project folder

    $ java -jar eureka-server/target/eureka-server-1.0-SNAPSHOT.jar
    $ java -jar customer/target/customer-1.0-SNAPSHOT.jar
    $ java -jar notification/target/notification-1.0-SNAPSHOT.jar
    $ java -jar fraud/target/fraud-1.0-SNAPSHOT.jar


# package Jars to docker images

        SPRING BOOT MAVEN PLUGIN AN
        REFERENCES: 

        https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/
        https://github.com/GoogleContainerTools/jib
        https://hub.docker.com/_/adoptopenjdk
        https://hub.docker.com/_/eclipse-temurin/


        sub module plugin:
        https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

        add profiles to the microservice
        <profiles>
            <profile>
                <id>build-docker-image</id>
                <build>
                    <plugins>
                        <plugin>
                            <groupId>com.google.cloud.tools</groupId>
                            <artifactId>jib-maven-plugin</artifactId>
                        </plugin>
                    </plugins>
                </build>
            </profile>
        </profiles>

    login to docker desktop

    command prompt
    $ docker logout
    $ docker login

    goto apigw/customer/fraud/notification/eureka-server folder in command prompt and run
    $ mvn clean package -P build-docker-image

        sample docker builds:
        abhilashgd/apigw
        abhilashgd/customer
        abhilashgd/fraud
        abhilashgd/eureka-server
        abhilashgd/notification

Note: profiles tab should come inside intellij maven tab
we cannot build from intellij because of some bug so build from terminal using command
$ mvn clean package -P build-docker-image

        [INFO] ------------------------------------------------------------------------
        [INFO] Reactor Summary for abhilashgdservices 1.0-SNAPSHOT:
        [INFO]
        [INFO] abhilashgdservices ................................. SUCCESS [  0.038 s]
        [INFO] amqp ............................................... SUCCESS [  0.983 s]
        [INFO] clients ............................................ SUCCESS [  0.242 s]
        [INFO] customer ........................................... SUCCESS [02:38 min]
        [INFO] fraud .............................................. SUCCESS [01:44 min]
        [INFO] eureka-server ...................................... SUCCESS [01:48 min]
        [INFO] notification ....................................... SUCCESS [01:48 min]
        [INFO] apigw .............................................. SUCCESS [ 13.061 s]
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------


        Adding Eureka Server and ApiGW to Docker Compose

        inside docker-compose.yaml
        add following

         eureka-server:
            image: abhilashgd/eureka-server:latest
            container_name: eureka-server
            ports:
            - "8761:8761"
            depends_on:
            - zipkin

         apigw:
            image: abhilashgd/apigw:latest
            container_name: apigw
            ports:
            - "8083:8083"
            depends_on:
            - zipkin
            - eureka-server

        Docker Network
        
        eureka-server:
            image: abhilashgd/eureka-server:latest
            container_name: eureka-server
            ports:
            - "8761:8761"
            networks:
            - spring
            depends_on:
            - zipkin
        apigw:
            image: abhilashgd/apigw:latest
            container_name: apigw
            ports:
            - "8083:8083"
            networks:
            - spring
            depends_on:
            - zipkin
            - eureka-server

        Spring Profiles
            inside docker compose and for eureka server
            environment:
                 - SPRING_PRIFILES_ACTIVE=docker

        create a application-docker.yml for apigw and eureka server
        chnage local host to zipkin in application-docker.yml

    ApiGW and Eureka Server Docker Containers

        run following commands
        $  mvn clean package -P build-docker-image
        $ docker compose up -d
        $ docker logs apigw
        $ docker logs eureka-server

    Testing: 
    eureka server - http://localhost:8761
    http://localhost:9411/zipkin/
    ServiceName=eureka-server

    open POSTMAN and send POST request
    open   http://localhost:9411/zipkin/ clear serviceName, reload and add serviceName
    we should be able to see APIGW select it and run query


    Commands 
    $ docker compose up -d
    $ mvn clean package -P build-docker-image
    $ docker compose pull
    $ docker compose up -d
    $ docker ps --format=$FORMAT
    $ docker logs customer
    $ docker logs fraud
    $ docker logs notification

        `POSTMAN TESTING
        POST: localhost:8083/api/v1/customers
        JSON RAW BODY

        {
            "firstName": "abhilash",
            "lastName": "gubbi",
            "email": "abhilashgubbi@test.com"
        }

        got zipkin: http://localhost:9411/zipkin/traces/5877ea5349a89eb0

        find a trace add serviceName
        we should be able to see all service names like customer, fraud, notificaiton, apigw and eureka-server

        goto eureka server: http://localhost:8761
        we should see all microservices loaded

        open pgadmin: http://localhost:5050/browser/#
        goto-->servers-->abhilashgd-->notification-->schemas-->public-->tables-->notification
        right click-->view/edit data-->all rows

        we should see all DB entries as we have persisted
        Query: SELECT * FROM public.notification ORDER BY notification_id ASC 


        $ docker compose stop //to stop all services
        $ docker compose start // to bring everything up
        $ docker compose down // to remove all containers running

`
# implement Kubernetes

    https://containerd.io
    
    Managed kubernetes -EKS from AWS
        - for running kubernetes
        - we can run using 
                AWS Fargate -for serverless containers
                or EC2 - for long running containers

    https://minikube.sigs.k8s.io/docs/start/

    **MINIKUBE**

    command to install minikube --> $ brew install minikube
        command to check Minikube client version -->  $ minikube version

        Goto docker --> preferences -->resources --> memory
        Increase the memory to minimum 6GB 

        Start minikube with 4g memory
        $ minikube start --memory=4g

        $ minikube status // to check status
            Example Status: 
            minikube
            type: Control Plane
            host: Running
            kubelet: Running
            apiserver: Running
            kubeconfig: Configured

        Minikube start --help // to check available options

        $ minikube ip // to check minikube ip

    **KUBECTL**

    https://kubernetes.io/
    https://kubernetes.io/docs/setup/
    https://kubernetes.io/docs/tasks/tools/

    kubectl install command for MAC M1 with Apple Silicon chip
    $    curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/darwin/arm64/kubectl"
    $ chmod +x ./kubectl
    $ sudo mv ./kubectl /usr/local/bin/kubectl
    $ sudo chown root: /usr/local/bin/kubectl
    $ kubectl version --client  // to check version
    $ kubectl cluster-info // to Verify kubectl configuration


    **PODS**

    is the smallest deployable unit and not container 
    its a group of containers

    never create pods on its own. use controllers instead

    within a pod we will have 
        -one main container (represents our application)
        - one init container (may or may not be present)
        - side containers (optional and acts as proxy to main container)
        - they communicate each other using localhost
        - pod itself has unique IP Address
        - ephermal and disposbale 
    Never deploy pods using kind:pod
    pods dont self heal
    we at least want one replica of our application running. thats why deploying pods on its own is dangerous

    **DEPLOYMENTS**

    Deployment creates replicaset and ensures desired set of pods always running.
    deployments handle version control of application deployments

    **SERVICES**

    - had stable IP address which never changes
    - stable DNS name and Stable port

    Types
        -cluster IP
        -NodePort
        -ExternalName
        -LoadBalancer

    **Service Discovery**

        mechanism for applications and microservices to locate each other on a network

    DNS: https://www.cloudflare.com/en-in/learning/dns/what-is-dns/
    https://coredns.io/

    **KUBEPROXY**
    Network proxy that runs on each node implementing part of the kubernetes service




# Deploy Postgres RabbitMQ and Zipkin to k8s

        Note: never deploy postgres in k8s in production 
        we can use Amazon RDS (Relational database service) in production. it manages DB for us.

         add k8s configmap,service,statefulset and volume

    goto abhilashgdsertvices-->k8s-->minikube folder
    run below command
    $ kubectl apply -f bootstrap/postgres

    we should see
    configmap/postgres-config created
    service/postgres created
    statefulset.apps/postgres created
    persistentvolume/postgres-pc-volume created
    persistentvolumeclaim/postgres-pc-volume-claim created

    we should see postgress running when we run below command
    $ kubectl get pods -w
    $ kubectl describe pod postgres-0
    $ kubectl logs postgres-0
    $ minikube ssh
    $ cd /mnt/
    $ ctrl + D
    to create a database inside the postgres running in k8s (ssh into running pod)
    $ kubectl exec -it postgres-0 -- psql -U abhilashgd
    $ \l
    $ create database customer;
    $ create database fraud;
    $ create database notification;
    $ ctrl + D
    $ kubectl get pods

    thats it. postgres is running on kubernetes

    goto abhilashgdsertvices-->k8s-->minikube folder
    run below command
    $ kubectl apply -f bootstrap/rabbitmq
    and
    $ kubectl apply -f bootstrap/zipkin

    to check in logs
    $ kubectl logs rabbitmq-0
    $ kubectl logs zipkin-0
    $ kubectl get all
    $ minikue service --url rabbitmq
    
    $ kubectl get services
    $ minikube tunnel 
    then access http://127.0.0.1:9411/zipkin/

# Refactor Microservices for k8s

        k8s offers a service type called LoadBalancer and this will provision a load balancer according to your cloud environment. I.e AWS, GCP or Azure.

        Disabling Eureka
        in all application.yml and application-docker.yml and for eureka server
        enabled: false

        goto-->clients module-->fraudClient.java
        inside client module resources 
        clients-default.properties

        set this in edit configurations for customer, fraud, notificaiton
        SPRING_PROFILES_ACTIVE=default

        POSTMAN TESTING

        POST: localhost:8085/api/v1/customers
        JSON BODY RAW: 
        {
            "firstName": "abhilash",
            "lastName": "gubbi",
            "email": "abhilashgubbi@test.com"
        }

        **KUBE PROFILE**

        mvn clean package -P build-docker-image
        docker compose pull

        POST: localhost:8085/api/v1/customers
        JSON BODY RAW: 
        {
            "firstName": "abhilash",
            "lastName": "gubbi",
            "email": "abhilashgubbi@test.com"
        }

        # Deploy Microservices to k8s

        under minikube folder, create a service folder and customer folder inside service
        deployment.yml
        service.yml

        under k8s folder
        $ kubectl apply -f minikube/services/customer
        $ kubectl get po
        $ kubectl describe pod customer-7d4ccf68c7-pcnhk
        $ kubectl logs customer-7d4ccf68c7-pcnhk -f
        $ kubectl get svc

        NAME         TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)                          AGE
        customer     LoadBalancer   10.111.54.56     <pending>     85:30773/TCP                     6m19s
        kubernetes   ClusterIP      10.96.0.1        <none>        443/TCP                          27h
        postgres     ClusterIP      10.101.149.141   <none>        5432/TCP                         5h34m
        rabbitmq     NodePort       10.97.3.141      <none>        15672:31672/TCP,5672:30672/TCP   4h58m
        zipkin       LoadBalancer   10.103.231.120   127.0.0.1     9411:30329/TCP                   4h58m


        $ kubectl get svc
        $ kubectl get svc | services
        $ cd minikube
        $ ls
        $ kubectl apply -f services/customer
        $ kubectl apply -f services/fraud
        $ kubectl apply -f services/notification
        $ kubectl get po

        if we get an error when we run kubectl get pods like below, it will be because of memory full. 

        Unable to connect to the server: net/http: TLS handshake timeout
        run below commands to run the containers again
        $ minikube delete
        $ minikube start
        $ minikube status
        $ Kubectl scale --replicas=0 deployment customer
        drop database customer;
        create database customer;
        $ Kubectl scale --replicas=1 deployment customer
        $ kubectl get pods
        $ kubectl get svc

        if we want to access load balancer then 
        $ minikube tunnel

# Implement KAFKA - REPLACE RABBITMQ

        - Kafka Documentation: https://kafka.apache.org/documentation/
        Introduction - 
        - Distributed Streaming Platform
        - Real time Event driven Application (example uber driver location update)
        - Fault tolerant
        - Scalable
        - Kafka runs a cluster,
        - Kafka connect source for data integration
        
        Producers send events of streams to kafka brokers and consumers recieve those events.it works through topics.
        Brokers running our Kafka process

        //Difference between Kafka and RABBITMQ
        in kafka, messages sent to the topic dont dissapear, they stay till retention period.
        in a traditional message queue, messages are gone as soon as they are consumed.
        
        Data Processing and transformation library
        - Data Transformation
        - Enrich Data
        - Filtering, Grouping, Aggregating, Joining and more

        Kafka Streams JAVA API
        - performs all data processing and puts it back to the topic so that consumers can consume
        
        //Kafka Topics - Producer - log - consumer
        Collection of Events,
        Replicated and partitioned,
        Durable - hours, days, years, forever
        Big or small

        GET Started - https://kafka.apache.org/quickstart
        Kafka Download - https://www.apache.org/dyn/closer.cgi?path=/kafka/3.1.0/kafka_2.13-3.1.0.tgz
        download from http link, 
        cd donwloads
        % tar -xzf kafka_2.13-3.1.0.tgz
        % rm -rf kafka_2.13-3.1.0.tgz
        %  cd kafka_2.13-3.1.0
        //Start Zookeper
        % bin/zookeeper-server-start.sh config/zookeeper.properties
        //Start Broker 
        % cd Downloads
        % cd kafka_2.13-3.1.0
        % bin/kafka-server-start.sh config/server.properties
        //We should see message like
            from now on will use broker 192.168.0.193:9092
        
        //BOOTSTRAP Project for kafka
        Spring Initialiser-->Name - Kafka-example Dependencies -  select spring web and kafka dependency

# implement Spring vault and secret management
# Implement reporting service

                
        





