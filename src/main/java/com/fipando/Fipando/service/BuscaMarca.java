package com.fipando.Fipando.service;

import com.fipando.Fipando.model.DadosMarcas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BuscaMarca {
    public List<DadosMarcas> listaBuscada(List<DadosMarcas> listaMarcas) {

        List<DadosMarcas> listaFiltrada = new ArrayList<>();

        while (listaFiltrada.size() !=1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o código ou um trecho do nome da marca:");
            String buscaMarca = scanner.next().toLowerCase();
            scanner.nextLine();
            listaFiltrada.clear();

            System.out.println("Confirma sua escolha");

            try {
                // Se for um número, busca pelo código
                int codigoBusca = Integer.parseInt(buscaMarca);
                listaFiltrada = listaMarcas.stream()
                        .filter(p -> p.getCodigo().equals(codigoBusca))
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                // Se não for número, busca pelo nome (removendo hífens para melhorar a busca)
                System.out.println("Buscando por trecho...");
                listaFiltrada = listaMarcas.stream()
                        .filter(p -> p.getMarca().replaceAll("-", "").toLowerCase().contains(buscaMarca))
                        .collect(Collectors.toList());
            }

            if (listaFiltrada.isEmpty()) {
                System.out.println("Nenhuma marca encontrada. Tente novamente.");
            } else {
                System.out.println("Marcas encontradas:");
                listaFiltrada.forEach(p -> System.out.printf("COD %-4d -- Marca: %s%n", p.getCodigo(), p.getMarca()));
                System.out.println("Digite novamente o código ou nome exato para confirmar:");
            }
        }

        return listaFiltrada;
    }

}
