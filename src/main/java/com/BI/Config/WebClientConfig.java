package com.BI.Config;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${external-apis.api1.base-url}")
    private String baseUrl;

    //configura el tiempo de coneccion
    @Value("${external-apis.api1.connection-timeout}")
    private int connectionTimeOut;

    //configurar la peticion a la api
    // configuramos la url a la que hacemos la peticions
    // el tiempo de conexion en milisegundos
    //tambien  se configura el tiempo de respuesta
    // implemtacmos intentos automaticos si una peticion falla
    // configuramos un limite de memoria

    @Bean
    public WebClient webClientApi1(){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeOut)
                .responseTimeout(Duration.ofMillis(connectionTimeOut));


        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    //configuracion Api2

    //configuracion Api 3

}
