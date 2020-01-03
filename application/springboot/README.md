# Spring Boot Application for Pet Bounded Context

## Running

```bash
java -jar build/libs/springboot-1.0.0.jar --spring.data.mongodb.uri=<MONGO DB URI>
```

Checking the health:

```bash
curl http://localhost:8080/actuator/health
```

Must results in:

```json
{
    "status": "UP"
}
```