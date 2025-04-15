API RESTful de Gestión de Usuarios - Spring Boot JPA
Java
Spring Boot
H2 Database

API RESTful para creación y gestión de usuarios con autenticación JWT, desarrollada con Spring Boot y JPA.

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

Compilar y ejecutar:

mvn clean install
mvn spring-boot:run
Acceder a la consola H2:

URL: http://localhost:8012/h2-console

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (dejar vacío)

📊 Estructura del Proyecto

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

🔍 Endpoints Disponibles
1. Crear Usuario
   POST /users

Request:

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
Response Exitosa (201 Created):

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


Prueba con cURL

# PRUEBA CON POSTMAN

IMPORTAR EL SIGUIENTE CURL EN LA HERRAMIENTA
AJUSTAR PARAMETROS SEGUN SU PREFERENCIA

curl --location 'localhost:8012/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "ferwis anazco",
"email": "ferwis@prueba.com",
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

🛠️ Desarrollo Adicional
Para contribuir o personalizar:

Configuración JWT:
Modifica jwt.secret y jwt.expiration en application.properties
