package com.financial.Financial.repository;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Long> {

    @Query("""
              SELECT a FROM Acquisto a
              WHERE UPPER(a.cliente.codiceFiscale) = UPPER(:cf)
                AND UPPER(a.prodotto.nome) = 'PREVENTIVO'
              ORDER BY a.dataAcquisto DESC
            """)
    List<Acquisto> findPreventiviByCodiceFiscale(@Param("cf") String codiceFiscale);

    List<Acquisto> findByProdotto_NomeInOrderByIdDesc(Collection<String> nomi);

    // DA APPROVARE = prodotto.nome = 'Preventivo'
    List<Acquisto> findByProdotto_NomeOrderByIdDesc(String nome);

    // Se vuoi ancora usare il tipo Prodotto (non necessario, ma lo lascio se ti serve altrove)
    List<Acquisto> findByProdottoInOrderByIdDesc(Collection<Prodotto> prodotti);
    // Da approvare = prodotto.id = 2
    List<Acquisto> findByProdotto_IdOrderByIdDesc(Long prodottoId);

    // Approvate = prodotto.id in (4,5,6)
    List<Acquisto> findByProdotto_IdInOrderByIdDesc(Collection<Long> prodottoIds);
}




