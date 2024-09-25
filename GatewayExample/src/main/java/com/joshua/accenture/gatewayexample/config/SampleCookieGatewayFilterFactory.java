package com.joshua.accenture.gatewayexample.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Filtro personalizado que agrega una cookie a la respuesta de una solicitud.
 * Nota el nombre del filtro debe ser el sufijo de la clase oh definirse en la
 * clase
 * @autor joshua.carrasco
 * */
@Component
public class SampleCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<SampleCookieGatewayFilterFactory.ConfigurationCookieR> {
    private final Logger logger = LoggerFactory.getLogger(SampleCookieGatewayFilterFactory.class);

    @Value("${pre.gateway.filter.message}")
    private String preGatewayFilterMessage;

    @Value("${post.gateway.filter.message}")
    private String postGatewayFilterMessage;

    @Value("${cookie.name}")
    private String cookieName;

    @Value("${cookie.value}")
    private String cookieValue;

    @Value("${filtro.personalizado.factory}")
    private String filterName;

    public SampleCookieGatewayFilterFactory() {
        super(ConfigurationCookieR.class);
    }

    @Override
    public GatewayFilter apply(ConfigurationCookieR config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            logger.info(preGatewayFilterMessage + config.message);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.value)
                        .ifPresent(cookie -> {
                    exchange.getResponse()
                            .addCookie(ResponseCookie.from(config.name, cookie).build());
                });
                logger.info(postGatewayFilterMessage + config.message);
            }));
        }, 100);
    }

    /**
     * Metodo que nos permite definir el orden de ejecucion del filtro
     * en caso de usar un filtro en cadena en el yml
     * Ejemplo:
     *  SampleCoockie: mensaje ejemplo , nombre , valor
     *
     * */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("message", "name", "value");
    }

    /**
     *  Definicion del Nombre del filtro
     * */
    @Override
    public String name() {
        return filterName;
    }

    /**
     * Forma de crear una clase interna con java 14.....17
     * clase interna por que solo la ocuparemos en esta clase
     * de lo contrario la sacamos
     * */
    public record ConfigurationCookieR(String name, String value, String message) {
    }

    /**
     *
     * Forma tradicional de crear una clase interna
     * con loombok
     * */
    @Getter
    @Setter
    public static class ConfigurationCookie {
        private String name;
        private String value;
        private String message;
    }
}
