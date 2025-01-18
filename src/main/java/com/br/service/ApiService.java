package com.br.service;

import com.br.dto.TipoCorteData;
import com.br.dto.api.RequestApi;
import com.br.dto.api.ResponseApi;
import com.br.dto.api.TipoCorteDataResponseApi;

import java.util.List;
import java.util.random.RandomGenerator;

public class ApiService {


    public ResponseApi callApi(RequestApi requestApi, List<TipoCorteData> cortes) {
        System.out.println("Calling API with request size: " + cortes.size());

        var cortesComPrecos = cortes.stream()
                .map(corte -> new TipoCorteDataResponseApi(corte.getId(), RandomGenerator.getDefault().nextInt(50))).toList();

        return ResponseApi.builder()
                .cnpj(requestApi.getCnpj())
                .nome(requestApi.getNome())
                .cortesPrecos(cortesComPrecos)
                .build();
    }

}
