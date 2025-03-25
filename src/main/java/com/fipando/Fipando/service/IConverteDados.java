package com.fipando.Fipando.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}