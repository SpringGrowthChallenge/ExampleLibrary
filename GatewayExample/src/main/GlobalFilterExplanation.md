# Explicación de la Clase `SampleGlobalFilter`

La clase `SampleGlobalFilter` es un filtro global en una aplicación Spring Cloud Gateway. Los filtros globales se aplican a todas las solicitudes que pasan a través del gateway.

## Descripción General

Esta clase implementa dos interfaces: `GlobalFilter` y `Ordered`. La primera define el comportamiento del filtro, mientras que la segunda establece la prioridad de ejecución del filtro.

## Componentes de la Clase

### Imports y Anotaciones

- **Imports**: Se importan varias clases necesarias para el funcionamiento del filtro.
- **Anotación `@Component`**: Indica que esta clase es un componente Spring, lo que permite que Spring la detecte y la gestione automáticamente.

### Logger

Se crea un logger para registrar información durante la ejecución del filtro.

### Método `filter`

Este método se ejecuta cada vez que una solicitud pasa a través del gateway.

#### Pre-Request

Antes de que la solicitud se envíe al backend, se añade un encabezado `token` con el valor `"abcdefg"`.

#### Post-Request

Después de que la respuesta se recibe del backend, se ejecuta un bloque de código que:

- Registra el valor del token en los encabezados de la solicitud.
- Añade el token a los encabezados de la respuesta.
- Añade una cookie llamada `color` con el valor `red` a la respuesta.

### Método `getOrder`

Define el orden de ejecución del filtro. Un valor más bajo indica una mayor prioridad.