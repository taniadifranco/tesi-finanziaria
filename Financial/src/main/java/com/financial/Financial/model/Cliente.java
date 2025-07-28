package com.financial.Financial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String codiceFiscale;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Acquisto> acquisti;


}
