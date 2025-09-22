package com.financial.Financial.controller;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.model.dto.PraticaListDTO;
import com.financial.Financial.service.AcquistoService;
import com.financial.Financial.service.AcquistoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AcquistoController {

    @Autowired
    private AcquistoServiceImpl acquistoService;

    // ===== CRUD ACQUISTI (stessi endpoint, ma con prefisso /api/acquisti) =====

    @GetMapping("/acquisti")
    @Operation(summary = "Ottiene tutti gli acquisti", description = "Restituisce la lista completa degli acquisti effettuati")
    public List<Acquisto> getAllAcquisti() {
        return acquistoService.findAll();
    }

    @GetMapping("/acquisti/{id}")
    @Operation(summary = "Ottiene un acquisto per ID", description = "Restituisce un singolo acquisto dato il suo ID")
    public Acquisto getAcquistoById(@PathVariable Long id) {
        return acquistoService.findById(id);
    }

    @PostMapping("/acquisti")
    @Operation(summary = "Crea un nuovo acquisto", description = "Registra un nuovo acquisto nel database")
    public Acquisto createAcquisto(@RequestBody Acquisto acquisto) {
        return acquistoService.save(acquisto);
    }

    @PutMapping("/acquisti/{id}")
    @Operation(summary = "Aggiorna un acquisto esistente", description = "Modifica i dati di un acquisto esistente dato il suo ID")
    public Acquisto updateAcquisto(@PathVariable Long id, @RequestBody Acquisto acquisto) {
        acquisto.setId(id);
        return acquistoService.update(acquisto);
    }

    @DeleteMapping("/acquisti/{id}")
    @Operation(summary = "Elimina un acquisto", description = "Rimuove un acquisto dal database dato il suo ID")
    public void deleteAcquisto(@PathVariable Long id) {
        acquistoService.deleteById(id);
    }

    // ===== ENDPOINT ADMIN (coincidono con il tuo FE: /api/admin/...) =====

    @GetMapping("/admin/pratiche/da-approvare")
    @Operation(summary = "Lista pratiche da approvare (Preventivo)")
    public List<PraticaListDTO> daApprovare() {
        return acquistoService.listDaApprovare();
    }

    @GetMapping("/admin/pratiche/approvate")
    @Operation(summary = "Lista pratiche approvate (Prestito/Mutuo)")
    public List<PraticaListDTO> approvate() {
        return acquistoService.listApprovate();
    }

    // opzionale: terza lista
    @GetMapping("/admin/pratiche/rifiutate")
    @Operation(summary = "Lista pratiche rifiutate")
    public List<PraticaListDTO> rifiutate() {
        return acquistoService.listRifiutate();
    }

    public record ApproveRequest(String prodottoFinale) {} // "Prestito" | "Mutuo"

    @PostMapping("/admin/preventivi/{id}/approve")
    @Operation(summary = "Approva un preventivo (promuove a Prestito/Mutuo e invia mail)")
    public ResponseEntity<Void> approve(@PathVariable Long id,
                                        @RequestBody(required = false) ApproveRequest body) {
        acquistoService.approva(id, body != null ? body.prodottoFinale() : null);
        return ResponseEntity.noContent().build();
    }

    public record RejectRequest(String motivo) {}

    @PostMapping("/admin/preventivi/{id}/reject")
    @Operation(summary = "Rifiuta un preventivo (marca come Rifiutato e invia mail)")
    public ResponseEntity<Void> reject(@PathVariable Long id, @RequestBody RejectRequest body) {
        acquistoService.rifiuta(id, body != null ? body.motivo() : null);
        return ResponseEntity.noContent().build();
    }
}
