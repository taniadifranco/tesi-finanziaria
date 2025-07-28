package com.financial.Financial.controller;

import com.financial.Financial.model.Prodotto;
import com.financial.Financial.service.ProdottoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    @Operation(summary = "Ottiene tutti i prodotti", description = "Restituisce la lista completa dei prodotti disponibili")
    public List<Prodotto> getAllProdotti() {
        return prodottoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ottiene un prodotto per ID", description = "Restituisce un singolo prodotto dato il suo ID")
    public Prodotto getProdottoById(@PathVariable Long id) {
        return prodottoService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea un nuovo prodotto", description = "Aggiunge un nuovo prodotto al database")
    public Prodotto createProdotto(@RequestBody Prodotto prodotto) {
        return prodottoService.save(prodotto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna un prodotto esistente", description = "Modifica i dati di un prodotto esistente dato il suo ID")
    public Prodotto updateProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        prodotto.setId(id);
        return prodottoService.update(prodotto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un prodotto", description = "Rimuove un prodotto dal database dato il suo ID")
    public void deleteProdotto(@PathVariable Long id) {
        prodottoService.deleteById(id);
    }
}
