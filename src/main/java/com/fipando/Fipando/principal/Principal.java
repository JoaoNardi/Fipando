package com.fipando.Fipando.principal;

import com.fipando.Fipando.service.ConsomeApi;
import com.fipando.Fipando.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private ConsomeApi consomeApi = new ConsomeApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);

    public void inicia(){

        System.out.println("Qual tipo de veículo deseja buscar?");
        System.out.println("""
                (Digite o numero da opção!)
                1 - Carros
                2 - Motos
                3 - Caminhões
                """);


        String tipoBuscado = "";

        while (tipoBuscado.isEmpty()) {
            int numeroDigitado = scanner.nextInt();
            scanner.nextLine();
            switch (numeroDigitado) {
                case 1:
                    tipoBuscado = "carros";
                    break;
                case 2:
                    tipoBuscado = "motos";
                    break;
                case 3:
                    tipoBuscado = "caminhoes";
                    break;
                default:
                    System.out.println("digite uma opcao valida");

            }
        }


            System.out.println(tipoBuscado);

        String url = "https://parallelum.com.br/fipe/api/v1/"+ tipoBuscado +"/marcas";
        var json = consomeApi.obterDados(url);
        System.out.println(json);

    }
}
