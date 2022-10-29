# Cloud Parking
### Este projeto é um projeto de estudo baseado no desafio da Digital Innovation One onde é sugerido o gerenciamento de um estacionamento.

[![Java CI with Maven](https://github.com/victorwanderley1/cloud-parking/actions/workflows/maven.yml/badge.svg)](https://github.com/victorwanderley1/cloud-parking/actions/workflows/maven.yml)

## Foram utilizadas os seguintes recursos e práticas:
- Spring Web
- Spring Security
- Spring JPA
- JUnit
- PostgreSQL
- Rest Assured
- Test Containers
- Model Mapper
- Swagger
- Heroku
- Docker
- CI/CD
- RestFull

## Disponibilizado em:
https://vw-dev-cloud-parking.herokuapp.com/swagger-ui.html

Username: user

Password: abc@vw@123

# Formas de execução:

## Manual:

### Comando para instanciação Banco de Dados Local:
```
docker run --name parking-db -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_USER=admin -e POSTGRES_DB=parking -p 5432:5432 -d postgres:latest
```

#### Também devem ser configuradas as seguintes variáveis de ambiente, elas devem ter os mesmos valores das utilizadas no container:
- DB_URL  
- DB_USER  
- DB_PASSWORD 

### Executar via CLI o seguinte comando:

```
mvn spring-boot:run
```

## Via Docker Compose:

### Comando para instanciação da applicação via docker compose:
Na raiz do repositório executar o seguinte comando via CLI:  

```
docker-compose up -d
```
