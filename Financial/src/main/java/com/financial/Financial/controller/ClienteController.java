package com.financial.Financial.controller;

import com.financial.Financial.model.Cliente;
import com.financial.Financial.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Ottiene tutti i clienti", description = "Restituisce la lista completa dei clienti registrati")
    public List<Cliente> getAllClienti() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Ottiene un cliente per ID", description = "Restituisce un singolo cliente dato il suo ID")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea un nuovo cliente", description = "Aggiunge un nuovo cliente al database")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna un cliente esistente", description = "Modifica i dati di un cliente esistente dato il suo ID")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.update(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un cliente", description = "Rimuove un cliente dal database dato il suo ID")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}
