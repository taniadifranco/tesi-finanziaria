package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import com.financial.Financial.model.dto.PraticaListDTO;
import com.financial.Financial.repository.AcquistoRepository;
import com.financial.Financial.repository.ProdottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AcquistoServiceImpl implements AcquistoService {


    private final AcquistoRepository acquistoRepository;
    private final ProdottoRepository prodottoRepository;
    private final MailService mailService;

    // IDs "stato" (adatta se nel tuo DB sono diversi)
    private static final long ID_PREVENTIVO = 2L; // pending
    private static final long ID_RIFIUTATO  = 3L;
    private static final long ID_APPROVATO  = 4L;
    private static final long ID_PRESTITO   = 5L;
    private static final long ID_MUTUO      = 6L;

    // CRUD base
    @Override public List<Acquisto> findAll() { return acquistoRepository.findAll(); }
    @Override public Acquisto findById(Long id) { return acquistoRepository.findById(id).orElseThrow(); }
    @Override public Acquisto save(Acquisto a) { return acquistoRepository.save(a); }
    @Override public Acquisto update(Acquisto a) {
        if (a.getId()==null || !acquistoRepository.existsById(a.getId())) throw new RuntimeException("Acquisto non trovato");
        return acquistoRepository.save(a);
    }
    @Override public void deleteById(Long id) {
        if (!acquistoRepository.existsById(id)) throw new RuntimeException("Acquisto non trovato");
        acquistoRepository.deleteById(id);
    }

    // Liste
    @Override
    public List<PraticaListDTO> listDaApprovare() {
        return acquistoRepository.findByProdotto_IdOrderByIdDesc(ID_PREVENTIVO)
                .stream().map(this::toListDTO).toList();
    }

    @Override
    public List<PraticaListDTO> listApprovate() {
        return acquistoRepository.findByProdotto_IdInOrderByIdDesc(List.of(ID_APPROVATO, ID_PRESTITO, ID_MUTUO))
                .stream().map(this::toListDTO).toList();
    }

    @Override
    public List<PraticaListDTO> listRifiutate() {
        return acquistoRepository.findByProdotto_IdOrderByIdDesc(ID_RIFIUTATO)
                .stream().map(this::toListDTO).toList();
    }

    // Azioni
    @Override
    @Transactional
    public void approva(Long id, String ignored) {
        var a = acquistoRepository.findById(id).orElseThrow();
        a.setProdotto(prodottoRepository.getReferenceById(ID_APPROVATO)); // stato "Approvato"
        if (a.getDataAcquisto() == null) a.setDataAcquisto(LocalDate.now());
        var email = a.getCliente()!=null ? a.getCliente().getUtente().getEmail() : null;
        if (email!=null && !email.isBlank()) mailService.sendEsito(email, true, null, a);
    }

    @Override
    @Transactional
    public void rifiuta(Long id, String motivo) {
        var a = acquistoRepository.findById(id).orElseThrow();
        a.setProdotto(prodottoRepository.getReferenceById(ID_RIFIUTATO)); // stato "Rifiutato"
        var email = a.getCliente()!=null ? a.getCliente().getUtente().getEmail() : null;
        if (email!=null && !email.isBlank()) mailService.sendEsito(email, false, motivo, a);
    }

    // opzionale: da "Approvato" → contratto reale
    @Override
    @Transactional
    public void promuoviAContratto(Long id, String tipo) {
        var a = acquistoRepository.findById(id).orElseThrow();
        long target = switch (tipo == null ? "" : tipo.toLowerCase()) {
            case "mutuo" -> ID_MUTUO;
            default -> ID_PRESTITO;
        };
        a.setProdotto(prodottoRepository.getReferenceById(target));
    }

    private PraticaListDTO toListDTO(Acquisto a) {
        var c = a.getCliente();
        var full = ((c!=null? c.getNome() : "") + " " + (c!=null? c.getCognome() : "")).trim();
        var nomeProd = a.getProdotto()!=null ? a.getProdotto().getNome().toUpperCase() : "PREVENTIVO";
        var tasso = a.getTassoInteresse()!=null ? a.getTassoInteresse()
                : (a.getProdotto()!=null ? a.getProdotto().getTassoInteresse() : null);
        return new PraticaListDTO(a.getId(), full, nomeProd, a.getImportoRichiesto(), a.getDurataMesi(), tasso, a.getDataAcquisto());
    }
}

