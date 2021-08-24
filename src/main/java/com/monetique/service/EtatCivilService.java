package com.monetique.service;

import com.monetique.dto.ClientNniDto;

public interface EtatCivilService {

    public ClientNniDto getInfoNni(String nni) throws Exception;

}
