package com.financial.Financial.service;

import com.financial.Financial.model.Ruolo;

import java.util.List;

public interface RuoloService {

    List<Ruolo> findAll();
    Ruolo findById(Long id);
    Ruolo save(Ruolo ruolo);
    Ruolo update(Ruolo ruolo);
    void deleteById(Long id);
}



