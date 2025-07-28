package com.financial.Financial.service;

import com.financial.Financial.model.Prodotto;
import com.financial.Financial.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public List<Prodotto> findAll() {
        return prodottoRepository.findAll();
    }

    @Override
    public Prodotto findById(Long id) {
        Optional<Prodotto> prodotto = prodottoRepository.findById(id);
        return prodotto.orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + id));
    }

    @Override
    public Prodotto save(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    @Override
    public Prodotto update(Prodotto prodotto) {
        if (!prodottoRepository.existsById(prodotto.getId())) {
            throw new RuntimeException("Impossibile aggiornare: prodotto non trovato con id: " + prodotto.getId());
        }
        return prodottoRepository.save(prodotto);
    }

    @Override
    public void deleteById(Long id) {
        if (!prodottoRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: prodotto non trovato con id: " + id);
        }
        prodottoRepository.deleteById(id);
    }
}
