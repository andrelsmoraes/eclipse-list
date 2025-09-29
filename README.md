# eclipse-list

A Spring Boot REST API for managing and querying Eclipse events data. This application demonstrates Java backend development practices using Clean Architecture principles.

## Overview

The eclipse-list API provides management of eclipse data including different types of eclipses (solar and lunar variants) and their geographical visibility regions. The application serves as a showcase for enterprise Java development patterns and modern Spring Boot features.

## Features

- **REST API Operations**: CRUD operations for eclipse events
- **Advanced Filtering**: Query eclipses by type, region, and date
- **Clean Architecture**: Proper separation between domain, use case, and infrastructure layers
- **Multiple Data Sources**: DynamoDB for primary storage, Elasticsearch for enhanced search
- **Event Streaming**: Kafka integration for data synchronization between storage systems
- **Environment Profiles**: Separate configurations for development and production
- **Testing**: Unit tests and WebMvcTest coverage
- **Documentation**: Swagger and Javadoc for API and code documentation; SLF4J for logging

## Architecture

The project follows Clean Architecture principles, organizing code into distinct layers:

```
├── api/                # REST controllers, DTOs, mappers
├── application/        # Business logic and use cases
├── domain/             # Domain models and entities
└── infrastructure/     # External integrations (DB, Kafka, etc.)
```

## API Endpoints

### Eclipse Operations

````
POST /eclipses - Create new eclipse
GET /eclipses - List all eclipses
GET /eclipses/by-region - Filter eclipses by region
GET /eclipses/by-type - Filter eclipses by type
DELETE /eclipses/{id} - Delete eclipse by ID
````

### Region Operations

````
POST /regions - Create new region
GET /regions - List all regions
DELETE /regions/{id} - Delete region by ID
````

### Type Operations

````
GET /types - List all eclipse types
````

### Data Population (Development Profile)

````
POST /populate - Populate database with sample data
````

## Technology Stack

- **Framework**: Spring Boot 3.x
- **Database**: DynamoDB (primary), Elasticsearch (search)
- **Messaging**: Apache Kafka
- **Testing**: JUnit 5, Mockito, WebMvcTest
- **Documentation**: Javadoc, SLF4J Logging, Swagger
- **Build Tool**: Maven

## Getting Started

### Prerequisites
- Java 17+
- Docker (for DynamoDB, Elasticsearch and Kafka)

### Running the Application

1. Start DynamoDB, Elasticsearch, and Kafka using Docker:
    ```
    docker pull amazon/dynamodb-local
       
    docker run -p 8000:8000 amazon/dynamodb-local -jar DynamoDBLocal.jar -inMemory -sharedDb
    ```
    ``` 
    docker pull docker.elastic.co/elasticsearch/elasticsearch:8.6.0 
       
   docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:8.6.0
    ```
    ```
    docker-compose up
       
    docker exec -it kafka kafka-topics --create \                                  
        --topic eclipse-creation \
        --bootstrap-server localhost:9092 \
        --partitions 1 \
        --replication-factor 1
   
    docker exec -it kafka kafka-topics --create \                                  
           --topic eclipse-deletion \
           --bootstrap-server localhost:9092 \
           --partitions 1 \
           --replication-factor 1
    ```
2. To handle DynamoDB local setup, it is possible to install NoSQL Workbench for DynamoDB from AWS, then import one of the provided JSON files in the `dynamodb` folder to create the necessary tables.
3. Run the application:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```
4. Swagger UI is available at `http://localhost:8080/swagger-ui.html`

### Clearing Data

To clear Elasticsearch indices, run:

```bash
curl -X DELETE "http://localhost:9200/eclipses"
```

### API Usage Examples

```bash
curl -X POST http://localhost:8080/data/populate
```

```bash
curl -X POST http://localhost:8080/eclipses \
    -H "Content-Type: application/json" \
    -d '{
    "date": "2024-04-08",
    "type": "SOLAR_TOTAL",
    "regionIds": ["550e8400-e29b-41d4-a716-446655440000"]
}'
```

```bash
curl "http://localhost:8080/eclipses/by-type?type=SOLAR_TOTAL"
```

```bash
curl "http://localhost:8080/eclipses/by-region?regionId=8e2e3c86-2b0f-4334-af9e-fdd5276ea365"
```

## Testing

Run the complete test suite:

```bash
./mvnw test
```

## Development
This project evolves incrementally to explore different Java backend technologies and patterns. Future enhancements may include:
- Authentication and authorization with Spring Security
- Internationalization (i18n) support
- Caching with Spring or Redis
- Containerization with Docker and Kubernetes
- CI/CD pipeline setup with GitHub Actions or Jenkins
- Advanced search capabilities with Elasticsearch

## License
This project is for educational and demonstration purposes.