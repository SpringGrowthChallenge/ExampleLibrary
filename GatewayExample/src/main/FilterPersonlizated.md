### Explicación de la Clase `SampleCookieGatewayFilterFactory`

La clase `SampleCookieGatewayFilterFactory` es un filtro personalizado en Spring Cloud Gateway que agrega una cookie a la respuesta de una solicitud. A continuación se explica cada parte de la clase:

### Paquete y Importaciones
- **Paquete**: Define el paquete donde se encuentra la clase.
- **Importaciones**: Importa las clases necesarias para el funcionamiento del filtro.

### Definición de la Clase
- **@Component**: Marca la clase como un componente de Spring.
- **Extiende AbstractGatewayFilterFactory**: Indica que esta clase es una fábrica de filtros de gateway.
- **Logger**: Se utiliza para registrar mensajes de log.

### Constructor
- **Constructor**: Inicializa la clase con la configuración `ConfigurationCookieR`.

### Método `apply`
- **apply**: Aplica el filtro, agregando una cookie a la respuesta.
- **OrderedGatewayFilter**: Define el orden de ejecución del filtro.
- **Logger**: Registra mensajes antes y después de la ejecución del filtro.

### Método `shortcutFieldOrder`
- **shortcutFieldOrder**: Define el orden de los campos en la configuración del filtro.

### Método `name`
- **name**: Define el nombre del filtro.

### Clases Internas
- **ConfigurationCookieR**: Clase interna que define la configuración del filtro usando `record`.
- **ConfigurationCookie**: Clase interna tradicional con anotaciones de Lombok para generar getters y setters.

### Resumen
La clase `SampleCookieGatewayFilterFactory` es un filtro personalizado en Spring Cloud Gateway que agrega una cookie a la respuesta de una solicitud. Utiliza un `record` y una clase tradicional para definir la configuración del filtro y emplea `OrderedGatewayFilter` para especificar el orden de ejecución del filtro.