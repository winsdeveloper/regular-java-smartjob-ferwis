# API RESTful para creación y gestión de usuarios con autenticación JWT, desarrollada con Spring Boot y JPA.

📋 Tecnologías Utilizadas
Java 21

Spring Boot 3.4.4

Spring Data JPA

Spring Web MVC

Spring Validation

Base de datos: H2 (en memoria)

MapStruct: Para mapeo de DTOs

Lombok: Para reducir boilerplate code

JJWT: Para generación y validación de tokens JWT

Maven: Como sistema de construcción

🚀 Configuración e Instalación
Requisitos Previos
Java 21 JDK instalado

Maven 3.8+

Postman o similar para probar los endpoints

Pasos para Ejecutar
Clonar el repositorio:

bash
git clone https://github.com/winsdeveloper/regular-java-smartjob-ferwis.git
cd regular-java-smartjob-ferwis
Configurar variables de entorno:

Crea un archivo application.yml en src/main/resources con:

properties
# application.yml
server:
port: 8012

spring:
datasource:
url: jdbc:h2:mem:testdb
driver-class-name: org.h2.Driver
username: sa
password: ''
h2:
console:
enabled: true
path: /h2-console
settings:
web-allow-others: false
jpa:
database-platform: org.hibernate.dialect.H2Dialect
hibernate:
ddl-auto: update
show-sql: true
properties:
hibernate:
format_sql: true

# JWT Configuration
jwt:
secret: "3a7f...a2b1"  # Reemplaza con tu clave HEX de 128 caracteres
expiration: 86400000    # 24 horas en milisegundos

logging:
level:
org.springframework: INFO
com.test.users: DEBUG

# Compilar y ejecutar:

mvn clean install
mvn spring-boot:run
Acceder a la consola H2:

URL: http://localhost:8012/h2-console

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (dejar vacío)

# 📊 Estructura del Proyecto

src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── test/
│   │           └── users/
│   │               └── regular_java_smartjob/
│   │                   ├── config/       # Configuraciones
│   │                   ├── controller/   # Controladores REST
│   │                   ├── dto/          # Objetos de transferencia
│   │                   ├── entity/       # Entidades JPA
│   │                   ├── exception/    # Manejo de excepciones
│   │                   ├── mapper/       # Mapeo de objetos
│   │                   ├── repository/   # Repositorios JPA
│   │                   ├── service/      # Lógica de negocio
│   │                   └── Application.java
│   └── resources/
│       ├── application.properties
│       └── schema.sql 

# 🔍 Endpoints Disponibles
1. Crear Usuario
   POST /users

# Request:

json

{
"name": "Nombre del usuario",
"email": "email@valido.com",
"password": "contraseñaSegura123",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}

# Response Exitosa (201 Created):

json

{
"status": "success",
"message": "Usuario con id: fc2a649c-7db8-4a96-a1ec-63276acd8f9c guardado exitosamente",
"data": {
"id": "fc2a649c-7db8-4a96-a1ec-63276acd8f9c",
"created": "2025-04-17T18:11:43.509235",
"modified": "2025-04-17T18:11:43.509235",
"lastLogin": "2025-04-17T18:11:43.5026244",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXMyQHBydWViMWEuY29tIiwiaWF0IjoxNzQ0OTI3OTAzLCJleHAiOjE3NDUwMTQzMDN9.DVVOrJPoldsgtpMcqE_TAuv3UZLBldI8FdjOFVjSwSXz4WiZxr4SJ13lMOq3j78ITr12eAAsA33tRrgo1QYaIA",
"isActive": true
}
}
2. lista de usuarios GET /users

# Response Exitosa (200 OK):

json

{
"status": "success",
"message": "",
"data": [
{
"id": "3f53aea2-7db0-45e9-87ff-173fc29055c6",
"created": "2025-04-17T18:11:15.620214",
"modified": "2025-04-17T18:11:15.620214",
"lastLogin": "2025-04-17T18:11:15.411631",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXNAcHJ1ZWIxYS5jb20iLCJpYXQiOjE3NDQ5Mjc4NzUsImV4cCI6MTc0NTAxNDI3NX0.UARiCQ0Wcrmj0l5FonnG5KrMYWVPaRTVbgQDcGctOdNp_whTGB9yL4rh40VvEFegyw9F2835OWmRuosP2-r2XA",
"isActive": true
},
{
"id": "fc2a649c-7db8-4a96-a1ec-63276acd8f9c",
"created": "2025-04-17T18:11:43.509235",
"modified": "2025-04-17T18:11:43.509235",
"lastLogin": "2025-04-17T18:11:43.502624",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXMyQHBydWViMWEuY29tIiwiaWF0IjoxNzQ0OTI3OTAzLCJleHAiOjE3NDUwMTQzMDN9.DVVOrJPoldsgtpMcqE_TAuv3UZLBldI8FdjOFVjSwSXz4WiZxr4SJ13lMOq3j78ITr12eAAsA33tRrgo1QYaIA",
"isActive": true
}
]
}
3. obtener usuario by id GET /users/{ID}

# Response Exitosa (200 OK):
{
"status": "success",
"message": "Usuario con id: 3f53aea2-7db0-45e9-87ff-173fc29055c6 retornado exitosamente",
"data": {
"id": "3f53aea2-7db0-45e9-87ff-173fc29055c6",
"created": "2025-04-17T18:11:15.620214",
"modified": "2025-04-17T18:11:15.620214",
"lastLogin": "2025-04-17T18:11:15.411631",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXNAcHJ1ZWIxYS5jb20iLCJpYXQiOjE3NDQ5Mjc4NzUsImV4cCI6MTc0NTAxNDI3NX0.UARiCQ0Wcrmj0l5FonnG5KrMYWVPaRTVbgQDcGctOdNp_whTGB9yL4rh40VvEFegyw9F2835OWmRuosP2-r2XA",
"isActive": true
}
}

json


Prueba con cURL

curl --location 'localhost:8012/users/ba3eb019-b92b-411e-b6ee-597f80018114'

nota: colocar id obtenido del resultado del guardado del usuario, sino responde que no existe

# PRUEBA CON POSTMAN SAVE USER

IMPORTAR EL SIGUIENTE CURL EN LA HERRAMIENTA
AJUSTAR PARAMETROS SEGUN SU PREFERENCIA

curl --location 'localhost:8012/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "ferwis anazco",
"email": "ferwis@pruebaa.com",
"password": "prueba123456",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}'
🔐 Validaciones
Email: Formato válido y único en el sistema

Password:

Mínimo 8 caracteres

Al menos una letra y un número

Nombre: No vacío

Teléfonos: Lista opcional con formato específico

# PRUEBA CON POSTMAN GET USERS

curl --location 'localhost:8012/users'

 
🛠️ Desarrollo Adicional
Para contribuir o personalizar:

Configuración JWT:
Modifica jwt.secret y jwt.expiration en application.properties
