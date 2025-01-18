package com.br.dto.api;

import com.br.dto.TipoCorteData;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RequestApi {

    private String cnpj;
    private String nome;
    private List<TipoCorteData> cortes;

}
