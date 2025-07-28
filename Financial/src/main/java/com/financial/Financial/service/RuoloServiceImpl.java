package com.financial.Financial.service;

import com.financial.Financial.model.Ruolo;
import com.financial.Financial.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuoloServiceImpl implements RuoloService {

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<Ruolo> findAll() {
        return ruoloRepository.findAll();
    }

    @Override
    public Ruolo findById(Long id) {
        Optional<Ruolo> ruolo = ruoloRepository.findById(id);
        return ruolo.orElseThrow(() -> new RuntimeException("ruolo non trovato con id: " + id));
    }

    @Override
    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    @Override
    public Ruolo update(Ruolo ruolo) {
        if (!ruoloRepository.existsById(ruolo.getId())) {
            throw new RuntimeException("Impossibile aggiornare: ruolo non trovato con id: " + ruolo.getId());
        }
        return ruoloRepository.save(ruolo);
    }

    @Override
    public void deleteById(Long id) {
        if (!ruoloRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: ruolo non trovato con id: " + id);
        }
        ruoloRepository.deleteById(id);
    }
}
