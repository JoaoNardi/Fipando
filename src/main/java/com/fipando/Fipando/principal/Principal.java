package com.fipando.Fipando.principal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipando.Fipando.model.*;
import com.fipando.Fipando.service.ConsomeApi;
import com.fipando.Fipando.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    ObjectMapper mapper = new ObjectMapper();
    private ConsomeApi consomeApi = new ConsomeApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);

    String urlBase = "https://parallelum.com.br/fipe/api/v1/";

    public void inicia() throws JsonProcessingException {
        TipoBuscado tipoBuscado = new TipoBuscado(); //intacia a classe para escolher tipo
        tipoBuscado.escolhaTipo();
        String escolha = tipoBuscado.getVeiculoBuscado() ; //ativa metodo para escolha do tipo

        System.out.println(escolha);

        String urlTipo = urlBase + escolha +"/marcas/"; //constroi url para api
        var jsonMarcas = consomeApi.obterDados(urlTipo); //puxa o body do api com base no url

        List<DadosMarcas> listaMarcas = converteDados.obterLista(jsonMarcas, DadosMarcas.class); //puxa as marcas para lista

        listaMarcas.stream()
                .sorted(Comparator.comparing(DadosMarcas::marca)) //ordenando
                .forEach(p -> System.out.println((String.format("COD %-4d --  Marca: %s",p.getCodigo(),p.getMarca()))));//impressao das marcas



        System.out.println("Busque a marca através do codigo ou por um trecho do nome");
        var buscaMarca = scanner.next();
        scanner.nextLine();

        String urlMarca = urlTipo+buscaMarca+"/modelos/"; //modela o endereco

        var jsonModelos = consomeApi.obterDados(urlMarca); //gera o json

        Modelos modelos = converteDados.obterDados(jsonModelos, Modelos.class); // puxa uma das lista do json

        modelos.ListaModelos().stream() // lambda para imprimir os modelos
                .sorted(Comparator.comparing(DadosModelos::codigo))
                .forEach(m -> System.out.println(String.format("COD %-7d --  Modelo: %s", m.codigo(),m.modelo())));

        System.out.println("Busque o modelo através do código ou por um trecho do nome");
        var buscaModelo = scanner.next(); // input do modelo digtado
        scanner.nextLine();

        String urlModelo = urlMarca+buscaModelo+"/anos/"; // monta endereco
        var jsonAnos = consomeApi.obterDados(urlModelo);
        var anos = converteDados.obterLista(jsonAnos, DadosAnos.class);

        anos.stream() //impressao da lista
                .sorted(Comparator.comparing(DadosAnos::data))
                .forEach(a -> System.out.println(String.format("Versao: %s",a.anoTipo())));

        System.out.println("Escolha o ano para ver detalhes.");
        var buscaDetalhes = scanner.next() + "-" + tipoBuscado.getNumeroBuscado();
        scanner.nextLine();

        String urlDetalhes = urlModelo+buscaDetalhes;
        var jsonDetalhes = consomeApi.obterDados(urlDetalhes);
        var detalhes = converteDados.obterDados(jsonDetalhes, Detalhes.class);
        System.out.println(detalhes.toString());
    }
}
