[ ![Download](https://api.bintray.com/packages/newpetstore/docker/pet/images/download.svg) ](https://bintray.com/newpetstore/docker/pet/_latestVersion)

# Spring Boot Application for Pet Bounded Context

## Running

```bash
SPRING_DATA_MONGODB_URI='<MONGO DB URI>' \
CATEGORIES_HTTP_ENDPOINT_URL='<CATEGORIES HTTP URL>' \
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
CATEGORIES_HTTP_ENDPOINT_URL='http://localhost:8081' \
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
       -e CATEGORIES_HTTP_ENDPOINT_URL='<CATEGORIES HTTP URL>' \
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

## Deployment to Kubernetes

To install in kubernetes you may use our [helm chart](./k8s-helm).

But, you must to create the `pets-dv` namespace.

- Install
```bash
helm install --namespace='pets-dv' \
     --set configmap.SPRING_DATA_MONGODB_URI='<MONGO DB URI>' \
     --set configmap.CATEGORIES_HTTP_ENDPOINT_URL='<CATEGORIES HTTP URL>' \
     pets k8s-helm/
```

```bash
# Example for configmap.SPRING_DATA_MONGODB_URI, would be:
'mongodb://root:WP5BXFrJjP@mongodb.pets-dv.svc.cluster.local:27017/pets?authSource=admin'
```

- To uninstall
```bash
helm uninstall --namespace='pets-dv' pets
```

> The command above will use the
[image here](https://bintray.com/newpetstore/docker/pet).

### Expose using a public DNS

> If you are using minikute, ignore this step

TODO

### Testing

We are using Locust to load test our microservice:

```bash
docker run -p 8089:8089 \
       -e LOCUST_LOCUSTFILE_PATH='/locust-tasks/locustfile.py' \
       -e LOCUST_TARGET_HOST='<PUBLIC URL>' \
       --mount src=$(pwd)/test,target=/locust-tasks,type=bind \
       --name locust 'peterevans/locust:1.6'
```

Open http://localhost:8089 at you favorite browser.

## Consume the API

- `POST /pets`
```json
{
  "name"      : "",
  "bio"       : "",
  "birthdate" : "2020-01-30",
  "category"  : ""
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
