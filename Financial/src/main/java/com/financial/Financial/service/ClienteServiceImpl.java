package com.financial.Financial.service;

import com.financial.Financial.model.Cliente;
import com.financial.Financial.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException("cliente non trovato con id: " + id));
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new RuntimeException("Impossibile aggiornare: cliente non trovato con id: " + cliente.getId());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Impossibile eliminare: cliente non trovato con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}

