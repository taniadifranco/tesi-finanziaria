package com.financial.Financial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(unique = true)
    private String codiceFiscale;

    @OneToOne
    @JoinColumn(name = "utente_id")
    @JsonBackReference
    private Utente utente;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Acquisto> acquisti;

    public void addAcquisto(Acquisto acquisto) {
        acquisti.add(acquisto);
        acquisto.setCliente(this);
    }


}
