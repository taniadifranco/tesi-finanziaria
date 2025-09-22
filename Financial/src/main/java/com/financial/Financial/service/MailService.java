package com.financial.Financial.service;

import com.financial.Financial.model.Acquisto;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    public void sendEsito(String to, boolean approvato, String motivo, Acquisto a) {
        // TODO: integra JavaMailSender o il tuo provider
        // Per ora, log o no-op
        // System.out.printf("Mail a %s: approvato=%s, motivo=%s, acquisto=%d%n", to, approvato, motivo, a.getId());
    }
}