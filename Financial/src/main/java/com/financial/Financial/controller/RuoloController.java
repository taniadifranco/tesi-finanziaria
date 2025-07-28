package com.financial.Financial.controller;

import com.financial.Financial.model.Ruolo;
import com.financial.Financial.service.RuoloService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @GetMapping
    @Operation(summary = "Ottiene tutti i ruoli", description = "Restituisce la lista completa dei ruoli presenti nel sistema")
    public List<Ruolo> getAllRuoli() {
        return ruoloService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ottiene un ruolo per ID", description = "Restituisce un singolo ruolo dato il suo ID")
    public Ruolo getRuoloById(@PathVariable Long id) {
        return ruoloService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea un nuovo ruolo", description = "Aggiunge un nuovo ruolo al database")
    public Ruolo createRuolo(@RequestBody Ruolo ruolo) {
        return ruoloService.save(ruolo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna un ruolo esistente", description = "Modifica i dati di un ruolo esistente dato il suo ID")
    public Ruolo updateRuolo(@PathVariable Long id, @RequestBody Ruolo ruolo) {
        ruolo.setId(id);
        return ruoloService.update(ruolo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un ruolo", description = "Rimuove un ruolo dal database dato il suo ID")
    public void deleteRuolo(@PathVariable Long id) {
        ruoloService.deleteById(id);
    }
}
