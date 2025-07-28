package com.financial.Financial.service;

import com.financial.Financial.model.Cliente;

import java.util.List;


public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    Cliente update(Cliente cliente);
    void deleteById(Long id);
}
