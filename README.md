# Evaluación: JAVA

## Requerimiento.

    El requerimiento de la aplicación es el entregado por Neoirs y cuyo titulo es:

    Ejercicio_JAVA-Especialista_Integracion-BCI.pdf

## Consideración a la entrega.

    En archivo .gitignore se eliminaron las lineas que omitian la subida a git de las componentes de la carpeta .mvn.
    Esto tiene por finalidad permitir realizar la ejecución de la aplicación sin necesidad de realizar configuraciones adicionales.

# Desarrollo

## Alcances del desarrollo.

    El desarrollo de la aplicación considera las siguientes implementaciones y herramientas:

    Arquitectura Hexagonal
    Java 17
    Base de dados en memoria H2
    Swagger
    JWT para generación de Token requerido
    Unit test con cobertura sobre el 80%
    Maven 3.1.8 para la gestion de dependencias, Ciclo de vida del proyecto, Construcción y empaquetado del proyecto, etc.
    Los artefactos principales de Maven, usados en el proyecto, son las siguientes:
        - spring-boot-starter-web
        - springdoc-openapi-starter-webmvc-ui
        - io.jsonwebtoken
        - org.hibernate.validator
        - com.h2database
        - org.projectlombok
        - spring-boot-starter-test
        - plugin org.jacoco para coverage de pruebas unitarias
    Configuracion de regex (regular expression) en application.properties para Email y Password
    Token persistido con dato de email de usaurio
    DockerFile. Genera Imagen de la aplicación y permite su ejecución en docker
        

## Prequesitos

    Antes de comenzar, asegúrate de tener instalados los siguientes requisitos en tu entorno:
    
    - **JDK 17 [Descargar JDK](https://www.oracle.com/java/technologies)
    - **Maven 3.6.3** o superior: [Descargar Maven](https://maven.apache.org/download.cgi)
    - **Git**: [Descargar Git](https://git-scm.com/downloads)
    - **Docker: [Descargar ´Docker](https://www.docker.com/products/docker-desktop) para generación de imagen de la aplicación y posterior ejecución

## Clonar Repositorio

    El repositorio del proyecto esta en github y su url es 

        https://github.com/JorgeBravoA/api-user

    Clonar el repositorio de git en maquina local

    - git clone https://github.com/JorgeBravoA/api-user.git
    - cd api-user

## Compilación y construcción del proyecto

    En un terminal del IDE o del S.O., en la ubicación del directorio api-user, ejecutar

    - mvn clean install

## Ejecución de la Aplicación

### Ejecución con maven

    En un terminal del IDE o del S.O., en la ubicación del directorio api-user, ejecutar

    - mvn spring-boot:run

### Ejecución del Jar Empaquetado

    En un terminal del IDE o del S.O., en la ubicación del directorio api-user, ejecutar

    - java -jar target/api-user-0.0.1-SNAPSHOT.jar

### Ejecución desde el IDE

    Sobre la clase principal del proyecto (ApiUserApplication)  dar con el mouse doble click y ejecutar run

### Ejecución desde DOCKER.

    Se codifico archivo Dockerfile para generar imagen de la aplicación y su posterior ejecución. 

    Para ejecutar la aplicación en Docker, seguir los siguientes pasos

    -  Generación de Imagen. En un terminal del IDE o del S.O., en la ubicación del directorio api-user, ejecutar

        docker build -t api-user .

    - Ejecución de la aplicación. En un terminal ejecutar

        docker run -p 8090:8090 api-user  (permite ver la ejecución de la aplicación)
        
        docker run -d -p 8090:8090 api-user  (levanta la aplicación si mostrar la ejecución de la aplicación)

    Una vez levantada la aplicación en docker se puede probar con postman o swagger.


## Pruebas de la ejecución de la aplicación y coverage de las pruebas.

    Una vez levantada la aplicación con alguno de los comandos del punto anterior, Y para probar la aplicación ejecutar con Postman seguir las siguientes indicaciones

### Prueba con Postman

#### Descargar de Collection

    - Descargar desde caprpeta del proyecto postman la collection api-user.postman_collection.json
    - Importar collection a postman
    - Ejecutar primera prueba con el body de la collection

#### Importar Curl

    - En postman importar el siguiente Curl

        curl --location 'localhost:8090/user' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "name": "Jorge Bravo",
        "email": "jhba@correo.com",
        "password": "alcaDtes12?*",
        "phones": [
        {
        
                    "number": "7654321",
                    "citycode": "2",
                    "countrycode": "75"
                },
                {
        
                    "number": "7654321",
                    "citycode": "2",
                    "countrycode": "75"
                }
            ]
        }'

    - Ejecutar primera prueba con el body de la collection

## SWAGGER

    Se encuentra habilitado Swagger para la prueba de la aplicación.

    - Ejecutar la aplicación
    - En browse Google u otro escribir la siguiente Url

        http://localhost:8090/swagger-ui

    - Con la aplicación ejecutando en Swagger se pueden realizar pruebas similares a las realizadas en Postman


    


## UNIT TEST: Ejecución de la pruebas unitarias

    - Ejecutar en linea de comando del proyecto

        mvn clean test
    
    - Revisar cobertura de los Unit test

        Copiar el path absoluto del archivo:

            target/site/jacoco/index.html

        Copiar path absoluto copiado en browse Google u otro. Con esto se veran las pruebas realizadas y el coverage alcanzado


## Base de datos en memoria

    La base de datos usada para el proyecto es H2. Para revisar y validar los datos grabados, proceder de la siguiente forma

    - Ejecutar la aplicación
    - En Browse Google u otro escribir la siguiente url

        http://localhost:8090/h2-console

    - Levantara una pagina en la cual se debe ingresar los siguientes parametros:

        Driver Class : org.h2.Driver
        JDBC URL     : jdbc:h2:mem:neorisdb
        User Name    : sa
        Password     : en blanco (no escribir)

        Seleccionar Connect, con esto se accedera a ver la data de las tablas USUARIO y PHONE 

### Script generación de tables usuario y phone

    CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    is_active BOOLEAN,
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255)
    );

    CREATE TABLE phone (
    id UUID PRIMARY KEY,
    number VARCHAR(50),
    city_code VARCHAR(50),
    country_code VARCHAR(50),
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE
    );
