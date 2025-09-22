package com.financial.Financial.service;

import com.financial.Financial.model.Cliente;
import com.financial.Financial.model.Ruolo;
import com.financial.Financial.model.Utente;
import com.financial.Financial.repository.RuoloRepository;
import com.financial.Financial.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente findById(Long id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        return utente.orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
    }

    @Override
    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Override
    public Utente update(Utente utente) {
        if (!utenteRepository.existsById(utente.getId())) {
            throw new RuntimeException("Impossibile aggiornare: utente non trovato con id: " + utente.getId());
        }
        return utenteRepository.save(utente);
    }

    @Override
    public void deleteById(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: utente non trovato con id: " + id);
        }
        utenteRepository.deleteById(id);
    }

    @Override
    public Optional<Utente> findByUsernameAndPassword(String username, String password) {
        return utenteRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void registerUser(Utente utente) {
        if (utenteRepository.findByUsername(utente.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username già esistente");
        }
        if (utenteRepository.findByEmail(utente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email già registrata");
        }

        Ruolo ruoloUser = ruoloRepository.findByNome("USER")
                .orElseThrow(() -> new RuntimeException("Ruolo USER non trovato"));

        utente.setRuolo(ruoloUser);

        Cliente cliente = new Cliente();
        cliente.setUtente(utente);
        utente.setCliente(cliente);

        utenteRepository.save(utente); // cascata salverà anche Cliente
    }

}

