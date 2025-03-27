package com.fipando.Fipando.principal;

import java.util.List;
import java.util.Scanner;

public class TipoBuscado {
    private String veiculoBuscado = "";
    private String numeroBuscado = "";

    private final Scanner scanner = new Scanner(System.in);


    private String loopTipo() {
        String busca = "";
        List<String> lista = List.of("carros", "motos", "caminhoes");

        while (busca.isEmpty()) {
            System.out.println("Digite o tipo de veiculo ou numero correspondente:");
            String inputBusca = scanner.next().toLowerCase().trim();
            scanner.nextLine();


            if (inputBusca.length() > 1) {
                busca = lista.stream().filter(p -> p.contains(inputBusca)).findFirst().orElse("");
            } else busca = inputBusca;

            if (busca.isEmpty()) {
                System.out.println("Entrada invalida");

            }

        }
        return busca;
    }

    //metodo para definir o tipo buscado
    public void escolhaTipo() {
        veiculoBuscado = "";
        numeroBuscado = "";

        System.out.println("Qual tipo de veículo deseja buscar?");
        System.out.println("""
                (Digite o numero da opção!)
                1 - Carros
                2 - Motos
                3 - Caminhões
                """);


        var busca2 = loopTipo();


        while (veiculoBuscado.isEmpty()) {
            switch (busca2) {
                case "1" ,"carros" ->{
                    veiculoBuscado = "carros";
                    numeroBuscado = "1";
                }
                case "2" , "motos" ->{
                    veiculoBuscado = "motos";
                    numeroBuscado = "2";
                }
                case "3", "caminhões" ->{
                    veiculoBuscado = "caminhoes";
                    numeroBuscado = "3";
                }

                default ->System.out.println("digite uma opcao valida");


            }
        }
        validarEscolha();
    }
        private void validarEscolha() {
            System.out.println("\n" + "Atenção! Confirme sua escolha!");
            System.out.println("***Você selecionou: " + numeroBuscado + " " + veiculoBuscado + " ?***");
            System.out.println("(Digite -SIM- para prosseguir ou -NAO- para escolher outro tipo)" + "\n");

            String validacao = "";
            while (validacao.isEmpty()) {
                validacao = scanner.next().toLowerCase();
                scanner.nextLine();
                switch (validacao) {
                    case "sim" -> {
                        System.out.println("Busca confirmada!");
                        return;
                    }
                    case "nao" -> {
                        veiculoBuscado = "";
                        numeroBuscado = "";
                        escolhaTipo();
                        return;
                    }
                    default -> {
                        System.out.println("Opção invalida");
                        validacao = "";
                    }

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