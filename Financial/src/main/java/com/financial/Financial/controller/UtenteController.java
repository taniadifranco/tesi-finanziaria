package com.financial.Financial.controller;

import com.financial.Financial.model.LoginRequest;
import com.financial.Financial.model.Utente;
import com.financial.Financial.service.UtenteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    @Operation(summary = "Ottiene tutti gli utenti", description = "Restituisce la lista completa degli utenti registrati")
    public List<Utente> getAllUtenti() {
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ottiene un utente per ID", description = "Restituisce un singolo utente dato il suo ID")
    public Utente getUtenteById(@PathVariable Long id) {
        return utenteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea un nuovo utente", description = "Aggiunge un nuovo utente al database")
    public Utente createUtente(@RequestBody Utente utente) {
        return utenteService.save(utente);
    }

    @PostMapping("/login")
    @Operation(summary = "Ricerca un utente esistente", description = "Effettua l'accesso ai dati di un utente esistente attraverso username e password")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginData) {
        Optional<Utente> utente = utenteService.findByUsernameAndPassword(
                loginData.getUsername(), loginData.getPassword()
        );
        if (utente.isPresent()) {
            return ResponseEntity.ok(utente.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna un utente esistente", description = "Modifica i dati di un utente esistente dato il suo ID")
    public Utente updateUtente(@PathVariable Long id, @RequestBody Utente utente) {
        utente.setId(id);
        return utenteService.update(utente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un utente", description = "Rimuove un utente dal database dato il suo ID")
    public void deleteUtente(@PathVariable Long id) {
        utenteService.deleteById(id);
    }
}
