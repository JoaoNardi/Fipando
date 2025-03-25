package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosMarcas(@JsonAlias("codigo") Integer codigo,
                          @JsonAlias("nome") String marca) {

    public Integer getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "codigo"+":" + codigo +
                " marca"+":" + marca;
    }
}
