package com.financial.Financial.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreventivoDto {

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String telefono;
    private String tipo;
    private Double importo;
    private Integer durata;
    private String note;
    private Long id;
    private Double importoMensile;
    private Double tassoInteresse;

}
