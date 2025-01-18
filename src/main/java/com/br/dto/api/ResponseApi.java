package com.br.dto.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ResponseApi {

    private String cnpj;
    private String nome;
    private List<TipoCorteDataResponseApi> cortesPrecos;

}
