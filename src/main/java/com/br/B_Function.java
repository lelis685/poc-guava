package com.br;


import com.br.dto.MessageCompletaDTO;
import com.br.dto.TipoCorteData;
import com.br.dto.api.RequestApi;
import com.br.service.PrecificadorService;

import java.util.List;

public class B_Function {


    public static void main(String[] args) throws InterruptedException {
        var cortes = List.of(new TipoCorteData(1, "zero", "jo-1"),
                new TipoCorteData(2, "degrade", "jo-2"),
                new TipoCorteData(3, "tesoura", "jo-30"));
        RequestApi requestAPi = RequestApi.builder()
                .cnpj("123456789")
                .nome("marco")
                .cortes(cortes)
                .build();

        PrecificadorService precificadorService = new PrecificadorService();
        MessageCompletaDTO precificado = precificadorService.precificar(requestAPi);

        System.out.println(precificado);
    }
}
