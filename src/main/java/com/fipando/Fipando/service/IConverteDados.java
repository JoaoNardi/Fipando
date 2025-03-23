package com.fipando.Fipando.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {
    String obterDados(String json, Class classe);
}