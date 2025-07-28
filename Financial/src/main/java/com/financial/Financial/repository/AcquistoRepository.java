package com.financial.Financial.repository;

import com.financial.Financial.model.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {
}
