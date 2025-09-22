package com.financial.Financial.service;

import com.financial.Financial.model.dto.PreventivoDto;

import java.util.List;
import java.util.Optional;

public interface PreventivoService {
    void creaPreventivoDaForm(PreventivoDto dto);
    List<PreventivoDto> getPreventiviByCodiceFiscale(String codiceFiscale);
    Optional<PreventivoDto> findById(Long id);
    void deletePreventivoById(Long id);
}
