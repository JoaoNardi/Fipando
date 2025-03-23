package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Marca {
    private @JsonAlias("codigo") String codigo;
    private @JsonAlias("nome") String nome;
}
