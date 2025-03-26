package com.fipando.Fipando.principal;

import java.util.List;
import java.util.Scanner;

public class TipoBuscado {
        private String veiculoBuscado = "";
        private String numeroBuscado = "";

    Scanner scanner = new Scanner(System.in);

    //metodo para definir o tipo buscado
    public void escolhaTipo(){

        System.out.println("Qual tipo de veículo deseja buscar?");
        System.out.println("""
                (Digite o numero da opção!)
                1 - Carros
                2 - Motos
                3 - Caminhões
                """);

        var inputBusca = scanner.next();
        List<String> listaPossiveis = List.of("1","2","3");
        var busca = inputBusca;
        scanner.nextLine();


//        if (inputBusca.length() == 1){
//            busca = inputBusca;
//        }
//        if (inputBusca.length() == 1 && !listaPossiveis.contains(inputBusca.trim()))
//        {
//            throw new IllegalArgumentException("Erro! especifique melhor sua busca");
//        }
//
        System.out.println(busca);
        while (veiculoBuscado.isEmpty()) {
            switch (busca) {
                case "1" :
                case "carros":
                    veiculoBuscado = "carros";
                    numeroBuscado = "1";
                    break;
                case "2":
                case "motos":
                    veiculoBuscado = "motos";
                    numeroBuscado = "2";
                    break;
                case "3":
                case "caminhões":
                    veiculoBuscado = "caminhoes";
                    numeroBuscado = "3";
                    break;
                default:
                    System.out.println("digite uma opcao valida");

            }
        }
    }

    public String getVeiculoBuscado() {
        return veiculoBuscado;
    }

    public String getNumeroBuscado() {
        return numeroBuscado;
    }
}
