package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AcquistoService {
    List<Acquisto> findAll();
    Acquisto findById(Long id);
    Acquisto save(Acquisto  acquisto);
    Acquisto update(Acquisto  acquisto);
    void deleteById(Long id);


}
