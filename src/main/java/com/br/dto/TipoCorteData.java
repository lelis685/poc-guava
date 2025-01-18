package com.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoCorteData {

    private int id;
    private String tipoCorte;
    private String barbeiro;

}
