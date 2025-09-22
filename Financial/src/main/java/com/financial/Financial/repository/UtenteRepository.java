package com.financial.Financial.repository;

import com.financial.Financial.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsernameAndPassword(String username, String password);
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByUsername(String username);
}
