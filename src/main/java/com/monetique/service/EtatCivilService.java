package com.monetique.service;

import com.monetique.dto.ClientNni;

public interface EtatCivilService {

    public ClientNni getInfoNni(String nni) throws Exception;

}
