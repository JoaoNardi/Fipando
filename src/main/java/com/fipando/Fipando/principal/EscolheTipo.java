package com.fipando.Fipando.principal;

import java.util.Scanner;

public class EscolheTipo {
        private String tipoBuscado;

    public String getTipoBuscado() {
        return tipoBuscado;
    }

    //metodo para definir o tipo buscado, retorna o tipo
    private String menuTipo(){
        String tipoBuscado = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual tipo de veículo deseja buscar?");
        System.out.println("""
                (Digite o numero da opção!)
                1 - Carros
                2 - Motos
                3 - Caminhões
                """);

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
        return tipoBuscado;
    }

    //construtor personalizado para definir o tipo buscado
    public EscolheTipo() {
        this.tipoBuscado = menuTipo();
    }
}
