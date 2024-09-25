package com.joshua.accenture.gatewayexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * La clase `SampleGlobalFilter` es un filtro global en una aplicación Spring Cloud Gateway.
 * Los filtros globales se aplican a todas las solicitudes que pasan a través del gateway.
 */
@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Se crea un logger para registrar información durante la ejecución del filtro.
     */
    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);


    /**
     * Filtro con programacion reactiva, usando el flujo de datos de reactor
     * */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Antes de la ejecucion del request
        logger.info("ejecutando el filtro antes del request PRE");
        // es inmutable , por lo que nos da una copia y esa es la que agregamos al request
        exchange.getRequest().mutate().headers(h -> h.add("token", "abcdefg"));

                //ejecutamos el request
        /**
         * Este método se ejecuta cada vez que una solicitud pasa a través del gateway.
         * Pre-Request: Antes de que la solicitud se envíe al backend, se añade un encabezado `token` con el valor `"abcdefg"`.
         * Post-Request: Después de que la respuesta se recibe del backend, se ejecuta un bloque de código que:
         *   Registra el valor del token en los encabezados de la solicitud.
         *   Añade el token a los encabezados de la respuesta.
         *   Añade una cookie llamada `color` con el valor `red` a la respuesta.
         *
         */
        return chain.filter(exchange)
                //manejamos la respuesta
                .then(Mono.fromRunnable(() -> {
            logger.info("ejecutando filtro POST response");
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (token != null) {
                logger.info("token: " + token);
                exchange.getResponse().getHeaders().add("token", token);
            }
            //Forma con programacion funcional
            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token"))
                    .ifPresent(value -> {
                        logger.info("token2: " + value);
                        //agregamos a ala cabecera
                        exchange.getResponse().getHeaders().add("token2", value);
                    });

            exchange.getResponse()
                    .getCookies()
                    .add("color", ResponseCookie.from("color", "red")
                            .build());
            //para cambiar el tipo de respuesta
            // exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }


    /**
     * Filtro con programacion reactiva, usando el flujo de datos de reactor
     * para cuando se usa la dependencia Gateway en lugar de ReactiveGateway
     * */
    public void filter2(ServerWebExchange exchange, GatewayFilterChain chain){
        // Antes de la ejecución del request
        logger.info("ejecutando el filtro antes del request PRE");
        exchange.getRequest().mutate().headers(h -> h.add("token", "abcdefg"));

        // Ejecutamos el request
        chain.filter(exchange);

        // Manejamos la respuesta
        logger.info("ejecutando filtro POST response");
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token != null) {
            logger.info("token: " + token);
            exchange.getResponse().getHeaders().add("token", token);
        }

        Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token"))
                .ifPresent(value -> {
                    logger.info("token2: " + value);
                    exchange.getResponse().getHeaders().add("token2", value);
                });

        exchange.getResponse().getCookies()
                .add("color", ResponseCookie.from("color", "red").build());
    }
    /**
     * Define el orden de ejecución del filtro. Un valor más bajo indica una mayor prioridad.
     */
    @Override
    public int getOrder() {
        //Los filtros son encadenados por eso se necesitan prioridad
        return 100;
    }
}
