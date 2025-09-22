package com.financial.Financial.repository;

import com.financial.Financial.model.Prodotto;
import com.financial.Financial.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByNome(String nome);


}
