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
"message": null,
"data": {
"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"name": "Nombre del usuario",
"email": "email@valido.com",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
],
"created": "2025-04-15T12:00:00",
"modified": "2025-04-15T12:00:00",
"lastLogin": "2025-04-15T12:00:00",
"token": "eyJhbGciOiJIUzUxMiJ9...",
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
"id": "5c99250c-afe4-463a-ba40-7eabec482c36",
"name": "ferwis anazco",
"email": "ferwis@prueba.com",
"phones": [
{
"number": "1234567",
"cityCode": null,
"countryCode": null
}
],
"created": "2025-04-16T09:48:46.659575",
"modified": "2025-04-16T09:48:46.659575",
"lastLogin": "2025-04-16T09:48:46.395424",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXNAcHJ1ZWJhLmNvbSIsImlhdCI6MTc0NDgxMTMyNiwiZXhwIjoxNzQ0ODk3NzI2fQ.6yNcnBjM8tbyQltJK9qZVINr-Bsa-GyyudtAF8hWYC4xgr_MSaoc7ml28CLUuNNBkxPAn4s8Ig3oPVqMcoC6Lg",
"isActive": true
},
{
"id": "bdacc397-f50a-49b6-8273-a00745a3b71b",
"name": "ferwis anazco",
"email": "ferwis@pruebaa.com",
"phones": [
{
"number": "1234567",
"cityCode": null,
"countryCode": null
}
],
"created": "2025-04-16T09:49:02.712841",
"modified": "2025-04-16T09:49:02.712841",
"lastLogin": "2025-04-16T09:49:02.706494",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXNAcHJ1ZWJhYS5jb20iLCJpYXQiOjE3NDQ4MTEzNDIsImV4cCI6MTc0NDg5Nzc0Mn0.QwVDRBcZ_sIOn0ukCUwXdPfVa07Ywa-tPFkfggAPyl8O__k2no8fP8zkemuGn_zyubbXt0TA-2ba8F4ZV73zPA",
"isActive": true
}
]
}

3. obtener usuario by id GET /users/{ID}

# Response Exitosa (200 OK):
{
"status": "success",
"message": "Usuario con id: ffa7648d-a68e-4778-8552-4f34071e9366 retornado exitosamente",
"data": {
"id": "ffa7648d-a68e-4778-8552-4f34071e9366",
"name": "ferwis anazco",
"email": "ferwis@pruebaa.com",
"phones": [
{
"number": "1234567",
"cityCode": null,
"countryCode": null
}
],
"created": "2025-04-16T10:21:56.609684",
"modified": "2025-04-16T10:21:56.609684",
"lastLogin": "2025-04-16T10:21:55.998695",
"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmZXJ3aXNAcHJ1ZWJhYS5jb20iLCJpYXQiOjE3NDQ4MTMzMTUsImV4cCI6MTc0NDg5OTcxNX0.TQZvHq29hQtLRxL95FXEaOPOVJM1ozPNeiJ3NlJTZrJLUyszS5a6LkRgQP37dnJ4fCuOI3rR24N5j16XeYOHwg",
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
