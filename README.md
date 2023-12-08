# Recipe Application

This is a Spring Boot application that provides a RESTful Api for recipe application.

# Setup

To run the application, follow the steps

1. Clone the repository to your local machine
2. Make sure to have java 17 or higher installed
3. Open a terminal and navigate to the project's root directory.
4. Run the command `mvn spring-boot:run` to start the application
5. Run the command `mvn test` to run the tests
6. Application can be started via docker via running `docker-compose up -d`

The application will start, and the api end point and the swagger ui can be accessed via the following links

- Api end point: [http://localhost:9090/api/recipe/all](http://localhsot:9090/api/recipe/all) - if running via docker
  then port is 8080
- Swagger UI : [https://localhost:9090/swagger-ui/html](https://localhost:9090/swagger-ui/html) - if running via docker
  then port is 8080

## Application Structure

The application is structured using the following packages:

- `com.abn.recipe` : The base package containing the application class.
- `com.abn.recipe.controller` : The package containing the controller.
- `com.abn.recipe.dto` : The package containing the DTOs.
- `com.abn.recipe.entity` : The package containing the entities.
- `com.abn.recipe.exception` : The package containing the exception layer.
- `com.abn.recipe.model` : The package containing the model.
- `com.abn.recipe.repository` : The package containing the repository layer.
- `com.abn.recipe.service` : The package containing the service layer.

### Statistics Collection

The application has actuator enabled which helps us to view basic metrics

### Configuration

The application can be configured using a configuration file. The configuration properties are defined in
the `application.properties` file.
The configs can be different per environment

### API Endpoints

The restful backend should support the following endpoints:

| Method | Path             | Description                                |
|--------|------------------|--------------------------------------------|
| GET    | /api/recipe/all  | Returns all the recipes                    |
| GET    | /api/recipe/{id} | Returns the recipe corresponding to the id |
| POST   | /api/recipe      | Creates a recipe                           |
| PUT    | /api/recipe      | Updates the corresponding recipe           |
| DELETE | /api/recipe/{id} | Deletes the recipe                         |
| POST   | /api/search      | Searches for the recipes based on the data |

### Docker

The application is dockerized, so it can be run from the container.

### Improvements

1. Monitoring in the same docker-compose
2. Caching