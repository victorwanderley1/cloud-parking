# Cloud Parking
### Este projeto é um projeto de estudo baseado no desafio da Digital Innovation One onde é sugerido o gerenciamento de um estacionamento.

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

## Comando para instanciação Banco de Dados Local:
```
docker run --name parking-db -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_USER=admin -e POSTGRES_DB=parking -p 5432:5432 -d postgres:latest
```

#### Também devem ser configuradas as seguintes variáveis de ambiente, elas devem ter os mesmos valores das utilizadas no container:
- POSTGRES_PASSWORD
- POSTGRES_USER
- POSTGRES_DB
