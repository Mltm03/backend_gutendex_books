package com.literalura.challenge.by.alura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class convierteDatos implements IConvierteDatos {

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
