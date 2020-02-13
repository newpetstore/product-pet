# Spring Boot Application for Pet Bounded Context

## Running

```bash
SPRING_DATA_MONGODB_URI='<MONGO DB URI>' \
java -jar build/libs/springboot-1.0.0.jar
```

> Get a free mongo instance [here](https://cloud.mongodb.com)

Or your own instance using Docker:

```bash
docker run -p 27017:27017 \
       --name petshop-db \
       mongo:3.4-xenial
```

Then, run the application:

```bash
SPRING_DATA_MONGODB_URI='mongodb://localhost:27017/test' \
java -jar build/libs/springboot-1.0.0.jar
```

### Using Docker

**Build**

```bash
docker build . -t pet:1.0.0
```

Or use the [docker-build.sh](./docker-build.sh) script.

```bash
./docker-build.sh '1.0.0'
```

**Run**

```bash
docker run -p 8080:8080 \
       -i --rm \
       -e SPRING_DATA_MONGODB_URI='<MONGO DB URI>' \
       pet:1.0.0
```

Or use the [docker-run.sh](./docker-run.sh) script.

```bash
./docker-run.sh '1.0.0'
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

## To change the logging configuration

We are using logback and you can change the log level and pattert using
environment variables.

- `APP_LOG_LEVEL`: set the global log level. Default is `INFO`

- `APP_LOG_PATTERN`: set the log message pattern. Default is
`%d{ISO8601} %-5level [%t] %c{1.}: %msg%n%throwable`

- `APP_LOG_LOGGERS`: to configure loggers. No default value.
```bash
# logger_1:LEVEL,logger_2:LEVEL,...
APP_LOG_LOGGERS='io.netty:ERROR,org.springframework:ERROR'
```
