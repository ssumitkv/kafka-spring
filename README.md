Welcome to my Kafka learning series! Here, I'll share my journey with Apache Kafka, from setting up the environment to mastering the CLI and beyond. For a deeper dive into all things Kafka, check out the extensive collection of articles on [Baeldung's Kafka tag](https://www.baeldung.com/tag/kafka).

Kafka is a powerful stream processing platform developed by the Apache Software Foundation, and I'll guide you through the essentials of getting started and effectively using it.

## Setting Up Kafka Using Docker on Mac

To quickly get Kafka up and running, Docker is an excellent choice. Here's a step-by-step guide:

I have created docker compose file for local setup [docker-compose](https://github.com/ssumitkv/kafka-spring/blob/main/scripts/single-zookeeper-single-kafka.yml).

### Start a Single Zookeeper and Kafka Broker )
```bash
docker-compose -f single-zookeeper-single-kafka.yml up -d
```
- `-d` runs the Docker app in the background.
- `-f` specifies the file, allowing you to skip the default `docker-compose.yml`.

### Check Service Status
```bash
docker-compose -f single-zookeeper-single-kafka.yml ps
```

### Run Commands from Kafka Docker Container
```bash
docker exec -it kafka1 /bin/bash
```

### Check Kafka Version
```bash
kafka-topics --version
```

### Stop Kafka
```bash
docker-compose -f single-zookeeper-single-kafka.yml stop
```

### Remove Kafka Completely
```bash
docker-compose -f single-zookeeper-single-kafka.yml down
```

### Docker Exec with Sudo
```bash
docker exec -u root -t -i container_id /bin/bash
```

For a detailed setup guide, visit [Conduktor's tutorial](https://www.conduktor.io/kafka/how-to-start-kafka-using-docker/).

## Using the Kafka CLI

### Managing Topics

**Create Topic**
```bash
kafka-topics --bootstrap-server localhost:9092 --topic demo_topic --create --partitions 3 --replication-factor 1
```

**List Topics**
```bash
kafka-topics --bootstrap-server localhost:9092 --list
```
Use `--exclude-internal` to hide internal topics.

**Describe Topic**
```bash
kafka-topics --bootstrap-server localhost:9092 --describe --topic demo_topic
```

**Alter Topic (Increase Partitions)**
```bash
kafka-topics --bootstrap-server localhost:9092 --alter --topic demo_topic --partitions 4
```

**Delete Topic**
```bash
kafka-topics --bootstrap-server localhost:9092 --delete --topic demo_topic
```

For more on topic configurations, see the [Kafka documentation](https://kafka.apache.org/documentation/#topicconfigs).

### Producer Operations

**Push Messages**
```bash
kafka-console-producer --bootstrap-server localhost:9092 --topic demo_topic
```

**Push with Key**
```bash
kafka-console-producer --bootstrap-server localhost:9092 --topic demo_topic --property parse.key=true --property key.separator=:
```

Explore more with [Conduktor's producer CLI tutorial](https://www.conduktor.io/kafka/kafka-producer-cli-tutorial/).

### Consumer Operations

**Consume Messages**
```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_topic
```

**Consume from Beginning**
```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_topic --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.value=true
```

Learn more about consumer CLI options on [Conduktor](https://www.conduktor.io/kafka/kafka-consumer-cli-tutorial/).

### Consumer Groups

**Create Consumer Group**
```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic topic_for_group_consumer --group my_first_application
```

**Check Group Details**
```bash
kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my_first_application
```

**Reset Offsets**
```bash
kafka-consumer-groups --bootstrap-server localhost:9092 --group my_first_application --reset-offsets --to-earliest --execute --topic topic_for_group_consumer
```

Continue exploring consumer group management [here](https://www.conduktor.io/kafka/kafka-consumer-group-management-cli-tutorial/).

## Kafka Connectors

Kafka Connectors simplify the integration between Kafka and other systems. For a vast selection, visit the [Connectors hub](https://www.confluent.io/hub/). Start learning about Kafka Connect [here](https://www.conduktor.io/kafka/kafka-connect-cli-tutorial/).

## Kafka Application Development

Kafka isn't just about message passing; it's about building robust applications. Here are some great resources to get started:

- [Kafka Programming Tutorials](https://www.conduktor.io/kafka/kafka-programming-tutorials/)
- [Java Kafka Programming](https://www.conduktor.io/kafka/java-kafka-programming/)
- [Advanced Kafka Consumer with Java](https://www.conduktor.io/kafka/advanced-kafka-consumer-with-java/)
- [Spring Kafka on Baeldung](https://www.baeldung.com/spring-kafka)

---

Stay tuned for more insights and tutorials on Kafka. Happy learning!

---