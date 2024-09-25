# Propósito del Proyecto

Este proyecto tiene como objetivo implementar un gateway utilizando Spring Cloud Gateway para gestionar y enrutar solicitudes a diferentes microservicios. A continuación se detallan los componentes y funcionalidades principales del proyecto:

## Componentes Principales

### 1. Filtros Personalizados
- **SampleGlobalFilter**: Un filtro global que se aplica a todas las solicitudes que pasan a través del gateway. Este filtro añade un encabezado `token` a las solicitudes y respuestas, y una cookie `color` a las respuestas.
- **SampleCookieGatewayFilterFactory**: Un filtro personalizado que agrega una cookie a la respuesta de una solicitud específica. Este filtro se configura mediante propiedades definidas en los archivos de configuración.

### 2. Configuración del Gateway
- **application.yml** y **application.properties**: Archivos de configuración que definen las rutas, predicados y filtros que se aplican a las solicitudes que pasan a través del gateway. Estos archivos permiten configurar el comportamiento del gateway de manera declarativa.

### 3. Controladores
- **EjemploController**: Un controlador de ejemplo que maneja solicitudes a la ruta `/api/libros`. Este controlador demuestra cómo se pueden utilizar los datos de los filtros en los métodos de manejo de solicitudes.

## Funcionalidades

### 1. Enrutamiento de Solicitudes
El gateway enruta las solicitudes a los microservicios correspondientes basándose en las rutas y predicados definidos en los archivos de configuración. Por ejemplo, las solicitudes a `/api/libreros/**` se enrutan al microservicio `msvc-libreros`.

### 2. Aplicación de Filtros
Los filtros definidos en el proyecto se aplican a las solicitudes y respuestas para modificar su contenido. Por ejemplo, el `SampleGlobalFilter` añade un encabezado `token` y una cookie `color` a las respuestas.

### 3. Configuración Declarativa
El comportamiento del gateway se configura de manera declarativa utilizando los archivos `application.yml` y `application.properties`. Esto permite definir rutas, predicados y filtros de manera sencilla y flexible.

## Resumen
En resumen, este proyecto implementa un gateway utilizando Spring Cloud Gateway para enrutar solicitudes a diferentes microservicios y aplicar filtros personalizados a las solicitudes y respuestas. La configuración del gateway se realiza de manera declarativa, lo que facilita la gestión y el mantenimiento del proyecto.