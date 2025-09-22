package com.financial.Financial.repository;

import com.financial.Financial.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    Optional<Prodotto> findByNomeIgnoreCase(String nome);
    Optional<Prodotto> findByNome(String nome);
}
