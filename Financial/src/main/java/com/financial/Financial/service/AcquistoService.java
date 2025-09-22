package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.model.dto.PraticaListDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AcquistoService {
    List<Acquisto> findAll();
    Acquisto findById(Long id);
    Acquisto save(Acquisto acquisto);
    Acquisto update(Acquisto acquisto);
    void deleteById(Long id);

    List<PraticaListDTO> listDaApprovare();
    List<PraticaListDTO> listApprovate();
    List<PraticaListDTO> listRifiutate();
    void promuoviAContratto(Long id, String tipo);
    void approva(Long id, String prodottoFinale /* "Prestito" | "Mutuo" (default Prestito) */);
    void rifiuta(Long id, String motivo);
}
