package com.financial.Financial.controller;

import com.financial.Financial.model.dto.PreventivoDto;
import com.financial.Financial.service.PreventivoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preventivo")
public class PreventivoController {

    @Autowired
    private PreventivoService preventivoService;

    @PostMapping
    @Operation(summary = "Invia richiesta di preventivo", description = "Crea un preventivo a partire dai dati del form pubblico")
    public ResponseEntity<String> creaPreventivo(@RequestBody PreventivoDto dto) {
        preventivoService.creaPreventivoDaForm(dto);
        return ResponseEntity.ok("Preventivo inviato correttamente.");
    }

    @GetMapping("/by-cf/{cf}")
    public List<PreventivoDto> getByCodiceFiscale(@PathVariable String cf) {
        return preventivoService.getPreventiviByCodiceFiscale(cf);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        preventivoService.deletePreventivoById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreventivoDto> getById(@PathVariable Long id) {
        return preventivoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
