package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AcquistoServiceImpl implements AcquistoService{
    @Override
    public List<Acquisto> findAll() {
        return List.of();
    }

    @Override
    public Acquisto findById(Long id) {
        return null;
    }

    @Override
    public Acquisto save(Acquisto acquisto) {
        return null;
    }

    @Override
    public Acquisto update(Acquisto acquisto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
