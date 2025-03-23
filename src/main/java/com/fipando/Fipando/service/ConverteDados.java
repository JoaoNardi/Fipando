package com.fipando.Fipando.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipando.Fipando.model.DadosMarcas;

import java.util.List;

public class ConverteDados {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar JSON para " + classe.getSimpleName(), e);
        }
    }

    public <T> List<T> obterListaDeDados(String json, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar JSON para lista", e);
        }
    }
}