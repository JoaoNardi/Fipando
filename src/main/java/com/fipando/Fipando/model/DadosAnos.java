package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAnos (@JsonAlias("codigo") String data,
                         @JsonAlias("nome")String anoTipo){

}
