package com.br;


import com.br.dto.MessageCompletaDTO;
import com.br.dto.TipoCorteData;
import com.br.dto.api.RequestApi;
import com.br.service.PrecificadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class B_Function {


    public static void main(String[] args) throws InterruptedException {
        List<TipoCorteData> cortes = generateCortes(10);

        RequestApi requestAPi = RequestApi.builder()
                .cnpj("123456789")
                .nome("marco")
                .cortes(cortes)
                .build();

        PrecificadorService precificadorService = new PrecificadorService();
        MessageCompletaDTO precificado = precificadorService.precificar(requestAPi);

        System.out.println(precificado);
    }

    private static List<TipoCorteData> generateCortes(int count) {
        List<TipoCorteData> cortes = new ArrayList<>(count);
        Random random = new Random();
        String[] tiposCorte = {"zero", "degrade", "tesoura", "maquina", "navalhado", "social"};
        
        for (int i = 0; i < count; i++) {
            int id = i + 1;
            String tipoCorte = tiposCorte[random.nextInt(tiposCorte.length)];
            String barbeiro = "jo-" + (random.nextInt(50) + 1);
            cortes.add(new TipoCorteData(id, tipoCorte, barbeiro));
        }
        
        return cortes;
    }
}
