package com.br.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageDTO {

    private String cnpj;
    private String nome;
    private List<TipoCorteData> cortes;

}
