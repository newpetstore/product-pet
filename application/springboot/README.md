# Spring Boot Application for Pet Bounded Context

## Running

```bash
java -jar build/libs/springboot-1.0.0.jar --spring.data.mongodb.uri=<MONGO DB URI>
```

> Get a free mongo instance [here](https://cloud.mongodb.com)

Or your own instance using Docker:

```bash
docker run -p 27017:27017 \
       --name petshop-db \
       mongo:3.4-xenial
```

Then, start the application:

```bash
java -jar build/libs/springboot-1.0.0.jar \
     --spring.data.mongodb.uri='mongodb://localhost:27017'
```

## Checking the health

```bash
curl http://localhost:8080/actuator/health
```

Must respond:

```json
{
    "status": "UP"
}
```