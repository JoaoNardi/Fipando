package com.fipando.Fipando.principal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipando.Fipando.model.*;
import com.fipando.Fipando.service.BuscaMarca;
import com.fipando.Fipando.service.ConsomeApi;
import com.fipando.Fipando.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    ObjectMapper mapper = new ObjectMapper();
    private final ConsomeApi consomeApi = new ConsomeApi();
    private final ConverteDados converteDados = new ConverteDados();
    private final Scanner scanner = new Scanner(System.in);

    String urlBase = "https://parallelum.com.br/fipe/api/v1/";

    public void inicia() throws JsonProcessingException {
        while (true) {
            TipoBuscado tipoBuscado = new TipoBuscado(); //intacia a classe para escolher tipo
            tipoBuscado.escolhaTipo();
            String escolha = tipoBuscado.getVeiculoBuscado(); //ativa metodo para escolha do tipo


            String urlTipo = urlBase + escolha + "/marcas/"; //constroi url para api
            var jsonMarcas = consomeApi.obterDados(urlTipo); //puxa o body do api com base no url

            List<DadosMarcas> listaMarcas = converteDados.obterLista(jsonMarcas, DadosMarcas.class); //puxa as marcas para lista


            listaMarcas.stream()
                    .sorted(Comparator.comparing(DadosMarcas::marca)) //ordenando
                    .forEach(p -> System.out.println((String.format("COD %-4d --  Marca: %s", p.getCodigo(), p.getMarca()))));//impressao das marcas


            System.out.println("Busque a marca através do codigo ou por um trecho do nome");



            String resultadoBusca = "";

            BuscaMarca buscaMarca = new BuscaMarca();
            List<DadosMarcas> listaFiltrada = buscaMarca.listaBuscada(listaMarcas);


            var continuar = scanner.nextLine().toLowerCase();
            while (!continuar.equalsIgnoreCase("sim")|| !continuar.equalsIgnoreCase("nao"))
                switch (continuar) {
                    case "sim" -> {
                        System.out.println("Continuando busca");
                        resultadoBusca = listaFiltrada.getFirst().getMarca().toString();
                        break;
                    }
                    case "nao" -> {
                        break;
                    }
                    default -> {
                        System.out.println("Digite uma resposta valida");
                        break;
                    }
                }


            String urlMarca = urlTipo + resultadoBusca + "/modelos/"; //modela o endereco

            var jsonModelos = consomeApi.obterDados(urlMarca); //gera o json

            Modelos modelos = converteDados.obterDados(jsonModelos, Modelos.class); // puxa uma das lista do json

            modelos.ListaModelos().stream() // lambda para imprimir os modelos
                    .sorted(Comparator.comparing(DadosModelos::codigo))
                    .forEach(m -> System.out.printf("COD %-7d --  Modelo: %s%n", m.codigo(), m.modelo()));

            System.out.println("Busque o modelo através do código ou por um trecho do nome");
            var buscaModelo = scanner.next(); // input do modelo digtado
            scanner.nextLine();

            String urlModelo = urlMarca + buscaModelo + "/anos/"; // monta endereco
            var jsonAnos = consomeApi.obterDados(urlModelo);
            var anos = converteDados.obterLista(jsonAnos, DadosAnos.class);

            anos.stream() //impressao da lista
                    .sorted(Comparator.comparing(DadosAnos::data))
                    .forEach(a -> System.out.printf("Versao: %s%n", a.anoTipo()));

            System.out.println("Escolha o ano para ver detalhes.");
            var buscaDetalhes = scanner.next() + "-" + tipoBuscado.getNumeroBuscado();
            scanner.nextLine();

            String urlDetalhes = urlModelo + buscaDetalhes;
            var jsonDetalhes = consomeApi.obterDados(urlDetalhes);
            var detalhes = converteDados.obterDados(jsonDetalhes, Detalhes.class);
            System.out.println(detalhes.toString());
        }
    }
}
