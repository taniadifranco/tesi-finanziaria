package com.financial.Financial.service;

import com.financial.Financial.model.Prodotto;

import java.util.List;

public interface ProdottoService {
    List<Prodotto> findAll();
    Prodotto findById(Long id);
    Prodotto save(Prodotto prodotto);
    Prodotto update(Prodotto prodotto);
    void deleteById(Long id);
}
