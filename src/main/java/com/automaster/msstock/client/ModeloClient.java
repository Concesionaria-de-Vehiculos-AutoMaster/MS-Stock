package com.automaster.msstock.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class ModeloClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public boolean existeModelo(Long idModelo) {
        try {
            // Hacemos una petición GET a MS-Modelos
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/modelos/" + idModelo)
                    .retrieve()
                    .toBodilessEntity()
                    .block(); // Esperamos la respuesta
            return true; // Si responde bien (200 OK), el modelo existe
        } catch (WebClientResponseException.NotFound ex) {
            return false; // Si responde 404, el modelo no existe
        }
    }
}