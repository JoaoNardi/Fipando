package com.fipando.Fipando.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fipando.Fipando.model.DadosMarcas;

import java.util.List;
import java.util.stream.Collectors;

public class ConverteDados implements IConverteDados{

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json,classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        try {
            CollectionType lista = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class,classe);
            return objectMapper.readValue(json,lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}