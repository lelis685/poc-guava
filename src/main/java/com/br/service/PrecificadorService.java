package com.br.service;


import com.br.dto.MessageCompletaDTO;
import com.br.dto.TipoCorteDataCompleto;
import com.br.dto.api.RequestApi;
import com.br.dto.api.TipoCorteDataResponseApi;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrecificadorService {


    public MessageCompletaDTO precificar(RequestApi requestApi) {
        long startTime = System.nanoTime();
        ApiService apiService = new ApiService();

        List<TipoCorteDataResponseApi> result = new ArrayList<>();

        Iterables.partition(requestApi.getCortes(), 100)
                .forEach(subListDeCortes -> {
                            List<TipoCorteDataResponseApi> cortesComPrecos = apiService.callApi(requestApi, subListDeCortes).getCortesPrecos();
                            result.addAll(cortesComPrecos);
                        }
                );

        Map<Integer, Integer> cortesResponseMap = result.stream()
                .collect(Collectors.toMap(TipoCorteDataResponseApi::getId, TipoCorteDataResponseApi::getPreco));

        List<TipoCorteDataCompleto> corteDataCompletos = requestApi.getCortes().stream().map(corte -> {
            Integer precoPorCorte = cortesResponseMap.get(corte.getId());
            return TipoCorteDataCompleto.builder()
                    .id(corte.getId())
                    .tipoCorte(corte.getTipoCorte())
                    .barbeiro(corte.getBarbeiro())
                    .preco(precoPorCorte)
                    .build();
        }).toList();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.printf("Execution time: %.3f ms%n", duration / 1_000_000.0);


        return MessageCompletaDTO.builder()
                .cnpj(requestApi.getCnpj())
                .nome(requestApi.getNome())
                .cortes(corteDataCompletos)
                .build();

    }


}
