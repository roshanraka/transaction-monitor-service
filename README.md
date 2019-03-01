# transaction-monitor-service

# Cloning the repo

```bash
git clone --recurse-submodules git@gitlab.com:DunyaLabs/orion/transaction-monitor-service.git
```

# Updating the repo with submodules

Use the following command to pull the changes made to sumbodule (elsewhere) and update the reference in this repo

```bash
git submodule update --remote
```

# pre-requisite

1. Java 1.8
2. Apache Maven 3
3. Kafka(topic="transactions") and ZooKeeper are running on Ports 9092, 2181 respectively.

In the root folder of this project run the following commands in a terminal

# to build
	mvn clean package

# to run
	java -jar target/transaction-monitor-service-1.0.jar

# docker

    Before running docker build check for dev/prod environemnt in src/main/resources/application.properties
    -docker build image
        docker build --build-arg APP_NAME=transaction-monitor-service -t orion/transaction-monitor-service .
    -docker run
        docker run -p 8808:8800 orion/transaction-monitor-service
        
Press Ctrl+C to exit


