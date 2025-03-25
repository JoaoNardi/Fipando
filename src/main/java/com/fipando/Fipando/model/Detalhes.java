package com.fipando.Fipando.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Detalhes(@JsonAlias("TipoVeiculo") Integer tipo,
                       @JsonAlias("Valor") String valor,
                       @JsonAlias("Marca") String marca,
                       @JsonAlias("Modelo") String modelo,
                       @JsonAlias("AnoModelo") Integer ano,
                       @JsonAlias("Combustivel") String combustivel,
                       @JsonAlias("CodigoFipe") String codigoFipe,
                       @JsonAlias("MesReferencia") String mensagem) {

    @Override
    public String toString() {
        String tipoVeiculo= "";

        switch (tipo){
            case 1: tipoVeiculo = "Carro";
            break;
            case 2: tipoVeiculo = "Moto";
            break;
            case 3: tipoVeiculo = "Caminhão";
            break;
            default: throw new RuntimeException("Erro ao listar detalhes de veiculo (`tipo`)");
        }
        return "Tipo de veiculo: " + tipoVeiculo + "\n" +
                marca  + " -- " + modelo + " ("+ano+")" + "\n" +
                "Combustivel: " + combustivel + "\n" +
                "Preço atual na tabela Fipe: " + valor + "\n" +
                "data de referência : " + mensagem + "\n" +
                "Codigo Fipe: " + codigoFipe;

    }
}
