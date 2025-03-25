package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(@JsonAlias("codigo") Integer codigo,
                           @JsonAlias("nome") String modelo) {
    @Override
    public Integer codigo() {
        return codigo;
    }

    @Override
    public String modelo() {
        return modelo;
    }
}
