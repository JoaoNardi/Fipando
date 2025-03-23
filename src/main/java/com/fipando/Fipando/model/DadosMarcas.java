package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosMarcas(@JsonProperty("codigo") Integer codigo,
                          @JsonProperty("nome") String marca) {

    public Integer getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\"" + " -- Marca: " + marca + '\n';


    }
}
