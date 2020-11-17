# bookstore backend application

## Build & Run

- Build package
```
mvn clean install
```
- Run docker-compose to build containers and run the project
```
docker-compose up -d --build
```
- Run docker-compose to stop the project
```
docker-compose down
```

## docker-compose.yml

### Components:
- **bookstore**: bookstore-api service

### Ports:
- bookstore-api Service : **__8080__** port is mapped to **__8080__** port of host

## Application Details:

### Swagger
Swagger is available under localhost:8080/swagger-ui.html

### H2 Databaase
H2 console is available under localhost:8080/h2-console

### Endpoints

| Service       | EndPoint                      | Method | Description                                      |
| ------------- | ----------------------------- | :-----:| ------------------------------------------------ |
| User Registration  | /user/register                | POST    | Registers a new user if it does not exist.
| Authentication  | /authenticate                | POST    | Authenticates requested user and returns a JWT as Bearer Token for authentication needed endpoints.
| Fetching orders of a user  | /orders                | GET    | Fetches given user's orders from H2 Database. Username is extracted from Bearer Token which is required in request header.
| Fetching an order's contents  | /orders/{order_id}                | GET    | Fetches contents of a unique order from H2 Database.
| Creating a new order  | /orders/create                | POST    | Creates a new order according to request with bookIds and count information. An order with multiple books can be created with multiple BookOrderModels.
