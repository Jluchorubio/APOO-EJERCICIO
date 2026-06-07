# INTEGRANTES:

- Jose Luis Rubio Cabrera
- Jheam Pierre Morales Henao
- Jhonnier David Castañeda
- Joan Alejandro Castañeda

# Tutoria03 - API de Estudiantes

Proyecto backend desarrollado con Spring Boot que expone una API REST para administrar estudiantes. La aplicacion permite crear, consultar, actualizar y eliminar registros de estudiantes almacenados en una base de datos MySQL.

## Tecnologias

- Java 17
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- MySQL
- Maven Wrapper

## Estructura del proyecto

```text
tutoria03/
  src/main/java/com/example/tutoria03/
    Tutoria03Application.java
    controllers/
      EstudianteController.java
    models/
      Estudiante.java
    repositories/
      IEstudianteRepository.java
    services/
      EstudianteService.java
  src/main/resources/
    application.properties
collection/
  Api-Estudiantes.postman_collection.json
```

## Que hace el proyecto

La API administra la entidad `Estudiante`, que contiene los siguientes campos:

- `id`
- `nombres`
- `correo`
- `numeroTelefono`
- `carrera`
- `direccion`
- `semestre`
- `activo`

El flujo interno es:

- `EstudianteController`: recibe las peticiones HTTP.
- `EstudianteService`: contiene la logica para consultar, crear, actualizar y eliminar estudiantes.
- `IEstudianteRepository`: usa Spring Data JPA para acceder a la base de datos.
- `Estudiante`: representa la tabla `estudiante` en MySQL.

## Configuracion

La configuracion principal esta en:

```text
tutoria03/src/main/resources/application.properties
```

Configuracion actual:

```properties
spring.application.name=tutoria03
server.port=8089
spring.datasource.url=jdbc:mysql://localhost:3306/grupo01
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

La aplicacion corre en:

```text
http://localhost:8089
```

## Requisitos

Antes de ejecutar el proyecto se necesita:

- Tener Java 17 o superior instalado.
- Tener MySQL instalado y ejecutandose.
- Tener creada la base de datos `grupo01`.
- Tener creada la tabla `estudiante`.

## Base de datos

Crear la base de datos:

```sql
CREATE DATABASE IF NOT EXISTS grupo01;
USE grupo01;
```

Crear la tabla:

```sql
CREATE TABLE IF NOT EXISTS estudiante (
  id INT PRIMARY KEY,
  nombres VARCHAR(255),
  correo VARCHAR(255),
  numero_telefono VARCHAR(255),
  carrera VARCHAR(255),
  direccion VARCHAR(255),
  semestre VARCHAR(255),
  activo BOOLEAN
);
```

Nota: en Java el atributo se llama `numeroTelefono`. Con la estrategia de nombres usada por Spring/Hibernate, normalmente se mapea a la columna `numero_telefono`.

## Como ejecutar

Desde la raiz del repositorio:

```powershell
cd tutoria03
.\mvnw.cmd spring-boot:run
```

Si todo esta correcto, la aplicacion queda disponible en:

```text
http://localhost:8089
```

## Como compilar

```powershell
cd tutoria03
.\mvnw.cmd -DskipTests package
```

El archivo `.jar` se genera en:

```text
tutoria03/target/tutoria03-0.0.1-SNAPSHOT.jar
```

## Endpoints

### Obtener todos los estudiantes

```http
GET /estudiantes
```

Ejemplo:

```powershell
Invoke-RestMethod -Uri "http://localhost:8089/estudiantes" -Method Get
```

### Obtener un estudiante por id

```http
GET /estudiantes/{id}
```

Ejemplo:

```powershell
Invoke-RestMethod -Uri "http://localhost:8089/estudiantes/1" -Method Get
```

### Crear un estudiante

```http
POST /estudiantes
```

Ejemplo:

```powershell
$body = @{
  id = 1
  nombres = "Carlos Medina"
  correo = "carlos@yopmail.com"
  numeroTelefono = "3107265423"
  carrera = "Sistemas"
  direccion = "carrera 6"
  semestre = "V"
  activo = $true
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8089/estudiantes" -Method Post -Body $body -ContentType "application/json"
```

### Actualizar un estudiante

```http
PUT /estudiantes/{id}
```

Ejemplo:

```powershell
$body = @{
  nombres = "Carlos Medina"
  correo = "carlos.medina@yopmail.com"
  numeroTelefono = "3127265423"
  carrera = "Sistemas"
  direccion = "carrera 6"
  semestre = "VIII"
  activo = $true
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8089/estudiantes/1" -Method Put -Body $body -ContentType "application/json"
```

### Eliminar un estudiante

```http
DELETE /estudiantes/{id}
```

Ejemplo:

```powershell
Invoke-WebRequest -Uri "http://localhost:8089/estudiantes/1" -Method Delete
```

## Coleccion de Postman

El proyecto incluye una coleccion de Postman en:

```text
collection/Api-Estudiantes.postman_collection.json
```

Se puede importar en Postman para probar la API manualmente.

Importante: el endpoint de actualizar debe usar el id en la URL:

```text
PUT http://localhost:8089/estudiantes/1
```
