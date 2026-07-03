# Gestao API

API REST em Java com Spring Boot, Spring Web, Spring Data JPA, Hibernate, Bean Validation, H2 para desenvolvimento e PostgreSQL preparado por profile.

## Requisitos

- Java 21+
- Maven 3.9+

## Como executar

```bash
mvn spring-boot:run
```

Base URL:

```text
http://localhost:8080/api
```

Swagger:

```text
http://localhost:8080/api/docs
```

H2 Console:

```text
http://localhost:8080/api/h2-console
```

## PostgreSQL

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

Variaveis suportadas:

```text
DB_URL=jdbc:postgresql://localhost:5432/gestaodb
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

## Endpoints

Clientes:

```text
GET    /api/v1/customers
GET    /api/v1/customers/{id}
POST   /api/v1/customers
PUT    /api/v1/customers/{id}
DELETE /api/v1/customers/{id}
```

Produtos:

```text
GET    /api/v1/products
GET    /api/v1/products/{id}
POST   /api/v1/products
PUT    /api/v1/products/{id}
DELETE /api/v1/products/{id}
```

## Estrutura

```text
br.com.gestao.api
  common
  customer
  product
```

Cada modulo segue a separacao `domain`, `dto`, `mapper`, `repository`, `service` e `web`.
