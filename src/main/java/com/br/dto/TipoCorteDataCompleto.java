package com.br.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoCorteDataCompleto {

    private int id;
    private String tipoCorte;
    private String barbeiro;
    private int preco;

}
