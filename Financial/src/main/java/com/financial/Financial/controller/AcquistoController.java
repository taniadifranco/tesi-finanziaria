package com.financial.Financial.controller;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.service.AcquistoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acquisti")
public class AcquistoController {

    @Autowired
    private AcquistoService acquistoService;

    @GetMapping
    @Operation(summary = "Ottiene tutti gli acquisti", description = "Restituisce la lista completa degli acquisti effettuati")
    public List<Acquisto> getAllAcquisti() {
        return acquistoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ottiene un acquisto per ID", description = "Restituisce un singolo acquisto dato il suo ID")
    public Acquisto getAcquistoById(@PathVariable Long id) {
        return acquistoService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea un nuovo acquisto", description = "Registra un nuovo acquisto nel database")
    public Acquisto createAcquisto(@RequestBody Acquisto acquisto) {
        return acquistoService.save(acquisto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna un acquisto esistente", description = "Modifica i dati di un acquisto esistente dato il suo ID")
    public Acquisto updateAcquisto(@PathVariable Long id, @RequestBody Acquisto acquisto) {
        acquisto.setId(id);
        return acquistoService.update(acquisto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un acquisto", description = "Rimuove un acquisto dal database dato il suo ID")
    public void deleteAcquisto(@PathVariable Long id) {
        acquistoService.deleteById(id);
    }
}
