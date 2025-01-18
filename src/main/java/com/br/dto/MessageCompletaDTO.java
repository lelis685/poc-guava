package com.br.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class MessageCompletaDTO {
    private String cnpj;
    private String nome;
    private List<TipoCorteDataCompleto> cortes;
}
