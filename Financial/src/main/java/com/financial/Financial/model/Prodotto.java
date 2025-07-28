package com.financial.Financial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // es: Prestito, Mutuo, Assicurazione
    private String descrizione;
    private Double tassoInteresse;
    private Integer durataMesi;
    private Double importoMinimo;
    private Double importoMassimo;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    private List<Acquisto> acquisti;

}
