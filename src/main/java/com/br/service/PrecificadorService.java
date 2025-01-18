package com.br.service;


import com.br.dto.MessageCompletaDTO;
import com.br.dto.TipoCorteDataCompleto;
import com.br.dto.api.RequestApi;
import com.br.dto.api.TipoCorteDataResponseApi;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrecificadorService {


    public MessageCompletaDTO precificar(RequestApi requestApi) {
        long startTime = System.nanoTime();

        ApiService apiService = new ApiService();

        Map<Integer, Integer> cortesResponseMap = new HashMap<>();

        Iterables.partition(requestApi.getCortes(), 2)
                .forEach(subListDeCortes -> {
                    apiService.callApi(requestApi, subListDeCortes).getCortesPrecos()
                            .forEach(corte -> cortesResponseMap.put(corte.getId(), corte.getPreco()));
                });

        List<TipoCorteDataCompleto> corteDataCompletos = requestApi.getCortes()
                .stream()
                .map(corte -> {
                    Integer preco = cortesResponseMap.get(corte.getId());
                    return TipoCorteDataCompleto.builder()
                            .id(corte.getId())
                            .tipoCorte(corte.getTipoCorte())
                            .barbeiro(corte.getBarbeiro())
                            .preco(preco)
                            .build();
                }).toList();

        MessageCompletaDTO result = MessageCompletaDTO.builder()
                .cnpj(requestApi.getCnpj())
                .nome(requestApi.getNome())
                .cortes(corteDataCompletos)
                .build();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.printf("Execution time: %.3f ms%n", duration / 1_000_000.0);

        return result;
    }

}



