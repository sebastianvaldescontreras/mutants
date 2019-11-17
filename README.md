# Mutans

Como no se ingles, esto lo escribo en español... es un proyecto simple, en el cual se genera dos endpoint
uno para ingresar el adn de un sujeto y ver si es mutante o humano (metodo: mutant); y otro endpoint para ver 
las estadisticas de todos los mutantes y humanos ingresados. (metodo:stat)

## Getting Started

Este codigo corre en un entorno local (springboot, gradle), corre en el puerto 8080, teniendo en cuenta que hay que 
generar una cola estandar en Simple Queue Service con el objetivo de tener los datos de los adn's ingresados que con 
un consumer de sqs, posteriormente guarda en base de datos Aurora DB (mutant-db). (Esto ya que la base de datos no aguanta tanto
en caso de hacer muchas peticiones por segundo, como es el caso)

### Prerequisites

- Intellij con plugin de lombok instalado y habilitar el procesamiento de anotaciones.
- Aws Simple Queue Service con Cola estandar y configuracion rapida llamada mutants-sqs.
- Amazon RDS con base de datos Aurora DB.
- Elastic Beanstalk con apache tomcat, para desplegar un war generado con gradle bootWar.
- Para entorno local Postgress (pgAdmin).
- Postman para realizar las llamadas y se encuentra en el proyecto el usado.


### Installing

Descargar el proyecto.
Si haz programado en java con springboot no deberia escribir tanto pero puedes consultarme al correo: 
sebastian.valdes.contreras@gmail.com
Configurar todos los datos en application.yml.
En este archivo se encuentran mis credenciales para acceder a los servicios de aws. Cambiar el nombre
agregado a "alenions" a todos las url y credenciales. Despues de 4 dias de leido esto, eliminare
todo. (sqs, base de datos, accesskey y secretkey de aws).

## Running the tests

Los Test unitarios se corren de forma local, dando click derecho en los test y pinchar "Run Test...",
elijes tu si quieres ver la covertura, hasta el momento que escribi esto, ni he revisado cuanto tiene y 
creo que no avance en esto.

## Authors

* **Sebastian Valdes Contreras** - *Estos son mis cachureos y siempre saco algo positivo. * - [Alenions](https://github.com/alenions/)
