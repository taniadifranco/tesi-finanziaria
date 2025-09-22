package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.model.Cliente;
import com.financial.Financial.model.Prodotto;
import com.financial.Financial.model.Utente;
import com.financial.Financial.model.dto.PreventivoDto;
import com.financial.Financial.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PreventivoServiceImpl implements PreventivoService {

    private final ProdottoRepository prodottoRepository;
    private final UtenteRepository utenteRepository;
    private final ClienteRepository clienteRepository;
    private final RuoloRepository ruoloRepository;
    private final AcquistoRepository acquistoRepository;

    @Override
    @Transactional
    public void creaPreventivoDaForm(PreventivoDto dto) {
        // 0) Recupera o crea il prodotto PREVENTIVO
        Prodotto prodotto = prodottoRepository.findByNomeIgnoreCase("PREVENTIVO")
                .orElseGet(() -> {
                    Prodotto p = new Prodotto();
                    p.setNome("PREVENTIVO");
                    p.setDescrizione("Richieste preventivo da form pubblico");
                    return prodottoRepository.save(p);
                });

        // 1) Recupera o crea l’utente (unico per email)
        Utente utente = utenteRepository.findByEmail(dto.getEmail())
                .orElseGet(() -> {
                    Utente u = new Utente();
                    u.setEmail(dto.getEmail());
                    u.setPassword(null); // se utente creato da form, non ha password
                    u.setRuolo(ruoloRepository.findByNome("USER")
                            .orElseThrow(() -> new IllegalStateException("Ruolo USER mancante")));
                    return utenteRepository.save(u);
                });

        // 2) Recupera cliente collegato all’utente o al CF
        Cliente cliente = clienteRepository.findByUtenteId(utente.getId())
                .orElseGet(() -> clienteRepository.findByCodiceFiscale(dto.getCodiceFiscale())
                        .orElse(null));

        if (cliente != null) {
            // aggiorno i campi (nel caso fosse vuoto o parzialmente compilato)
            cliente.setNome(dto.getNome());
            cliente.setCognome(dto.getCognome());
            cliente.setCodiceFiscale(dto.getCodiceFiscale());
            cliente.setUtente(utente);
            cliente = clienteRepository.save(cliente);
        } else {
            // se non c’era proprio nessun cliente, lo creo
            cliente = new Cliente();
            cliente.setNome(dto.getNome());
            cliente.setCognome(dto.getCognome());
            cliente.setCodiceFiscale(dto.getCodiceFiscale());
            cliente.setUtente(utente);
            cliente = clienteRepository.save(cliente);
        }


        // 3) Crea sempre un nuovo acquisto (preventivo)
        Acquisto acquisto = new Acquisto();
        acquisto.setCliente(cliente);
        acquisto.setProdotto(prodotto);
        acquisto.setImportoRichiesto(dto.getImporto());
        acquisto.setDurataMesi(dto.getDurata());
        acquisto.setTassoInteresse(dto.getTassoInteresse());   // calcolato in step successivi
        acquisto.setImportoMensile(dto.getImportoMensile());
        acquisto.setDataAcquisto(LocalDate.now());

        acquistoRepository.save(acquisto);
    }


    public List<PreventivoDto> getPreventiviByCodiceFiscale(String codiceFiscale) {
        return acquistoRepository.findPreventiviByCodiceFiscale(codiceFiscale) // List<Acquisto>
                .stream()
                .map(this::toDto)
                .toList();
    }
//    public List<PreventivoDto> getPreventiviByUserId(Long id) {
//        return acquistoRepository.findPreventiviByUserId(id) // List<Acquisto>
//                .stream()
//                .map(this::toDto)
//                .toList();
//    }

    private PreventivoDto toDto(Acquisto a) {
        PreventivoDto dto = new PreventivoDto();
        // Cliente
        if (a.getCliente() != null) {
            dto.setNome(a.getCliente().getNome());
            dto.setCognome(a.getCliente().getCognome());
            dto.setCodiceFiscale(a.getCliente().getCodiceFiscale());
            // Email dall'utente collegato (se esiste)
            if (a.getCliente().getUtente() != null) {
                dto.setEmail(a.getCliente().getUtente().getEmail());
            }
        }
        // Prodotto / Tipo
        if (a.getProdotto() != null) {
            dto.setTipo(a.getProdotto().getNome()); // es. "PREVENTIVO"
        }
        // Dati economici
        dto.setImporto(a.getImportoRichiesto());
        dto.setDurata(a.getDurataMesi());
        dto.setImportoMensile(a.getImportoMensile());
        dto.setTassoInteresse(a.getTassoInteresse());

        // Campi non presenti in Acquisto -> lascio null
        dto.setTelefono(null);
        dto.setNote(null);

        dto.setId(a.getId());

        return dto;
    }

    @Override
    public Optional<PreventivoDto> findById(Long id) {
        return acquistoRepository.findById(id)
                .map(this::toDto);
    }

    @Transactional
    @Override
    public void deletePreventivoById(Long id) {
        acquistoRepository.deleteById(id);
    }

}



