package com.fipando.Fipando.principal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fipando.Fipando.model.DadosMarcas;
import com.fipando.Fipando.model.Marca;
import com.fipando.Fipando.service.ConsomeApi;
import com.fipando.Fipando.service.ConverteDados;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    private ConsomeApi consomeApi = new ConsomeApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);

    public void inicia(){
            EscolheTipo escolheTipo = new EscolheTipo(); //intacia a classe para escolher tipo
            String tipoBuscado = escolheTipo.getTipoBuscado(); //ativa metodo para escolha do tipo

            System.out.println(tipoBuscado);

        String url = "https://parallelum.com.br/fipe/api/v1/"+ tipoBuscado +"/marcas"; //constroi url para api
        String json = consomeApi.obterDados(url); //puxa o body do api com base no url

        List<DadosMarcas> listaMarcas = converteDados
                .obterListaDeDados(json,new TypeReference<List<DadosMarcas>>() {}); //puxa as marcas para lista

        //impressao das marcas
        for (DadosMarcas marcas : listaMarcas){
            System.out.println(String.format("COD %-4d --  Marca: %s",marcas.getCodigo(),marcas.getMarca()));
        }

    }
}
