# Explicación del Archivo `application.yml`

Este archivo `application.yml` configura el comportamiento del Spring Cloud Gateway en el proyecto. A continuación se explica cada parte del archivo:

## Configuración del Gateway

### Sección `spring.cloud.gateway.routes`
Esta sección define las rutas y los filtros que se aplican a las solicitudes que pasan a través del gateway. Cada ruta tiene un `id`, una `uri`, una lista de `predicates` y una lista de `filters`.

#### Ruta `msvc-libreros`
- **id**: `msvc-libreros`
  - Identificador único de la ruta.
- **uri**: `lb://msvc-libreros`
  - URI del microservicio al que se enrutan las solicitudes.
- **predicates**:
  - **Path=/api/libreros/**: La ruta coincide con cualquier solicitud cuyo camino comience con `/api/libreros/`.
  - **Header=nombre, \d+**: La solicitud debe tener un encabezado `nombre` cuyo valor sea un dígito.
  - **Method=GET, POST**: La solicitud debe ser de tipo GET o POST.
  - **Query=nombre**: La solicitud debe tener un parámetro de consulta `nombre`.
  - **Cookie=nombre,valor**: La solicitud debe tener una cookie `nombre` con el valor `valor`.
  - **Header=Content-Type, application/json**: La solicitud debe tener un encabezado `Content-Type` con el valor `application/json`.
- **filters**:
  - **StripPrefix=2**: Elimina los dos primeros segmentos del camino de la solicitud.
  - **name: SampleCookie**: Aplica el filtro personalizado `SampleCookie`.
    - **args**:
      - **message**: `"Ejemplo de mensaje"`: Mensaje de ejemplo que se registra en el filtro.
      - **name**: `"nombre atributo"`: Nombre de la cookie que se agrega a la respuesta.
      - **value**: `"valor"`: Valor de la cookie que se agrega a la respuesta.

#### Ruta `msvc-items`
- **id**: `msvc-items`
  - Identificador único de la ruta.
- **uri**: `lb://msvc-libros`
  - URI del microservicio al que se enrutan las solicitudes.
- **predicates**:
  - **Path=/api/libros/**: La ruta coincide con cualquier solicitud cuyo camino comience con `/api/libros/`.
- **filters**:
  - **StripPrefix=2**: Elimina los dos primeros segmentos del camino de la solicitud.
  - **AddRequestHeader=nombreAtributo, valor**: Añade un encabezado `nombreAtributo` con el valor `valor` a la solicitud.
  - **AddResponseHeader=nombreAtributoHeader, valor**: Añade un encabezado `nombreAtributoHeader` con el valor `valor` a la respuesta.
  - **AddRequestParameter=nombreAtributoParametro, valor**: Añade un parámetro de consulta `nombreAtributoParametro` con el valor `valor` a la solicitud.
  - **SetResponseHeader=Content-Type, text/plain**: Establece el encabezado `Content-Type` de la respuesta a `text/plain`.

## Resumen
El archivo `application.yml` configura el Spring Cloud Gateway para enrutar solicitudes a diferentes microservicios (`msvc-libreros` y `msvc-items`) y aplicar filtros personalizados y predeterminados a las solicitudes y respuestas. Las rutas y filtros se definen de manera declarativa, lo que facilita la gestión y el mantenimiento del gateway.