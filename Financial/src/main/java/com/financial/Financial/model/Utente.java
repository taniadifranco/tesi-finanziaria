package com.financial.Financial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;


@Entity
@Getter
@Setter
@ToString
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;


    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
    private Cliente cliente;
}
