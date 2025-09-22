package com.financial.Financial.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PraticaListDTO {
    private Long id;
    private String cliente;
    private String prodotto;
    private Double importoRichiesto;
    private Integer durataMesi;
    private Double tassoInteresse;
    private LocalDate dataAcquisto;
}
