package com.financial.Financial.repository;

import com.financial.Financial.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCodiceFiscale(String codiceFiscale);
    Optional<Cliente> findByUtenteId (Long id);
}
