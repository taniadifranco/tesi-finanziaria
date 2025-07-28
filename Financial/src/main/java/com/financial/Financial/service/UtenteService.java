package com.financial.Financial.service;

import com.financial.Financial.model.Utente;
import java.util.List;
import java.util.Optional;


public interface UtenteService {
    List<Utente> findAll();
    Utente findById(Long id);
    Utente save(Utente utente);
    Utente update(Utente utente);
    void deleteById(Long id);
    Optional<Utente> findByUsernameAndPassword(String username, String password);

}
