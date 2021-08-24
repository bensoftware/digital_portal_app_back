package com.monetique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientNniDto {
    private ClientNni clientNni;
    private int erreurCode; // 0 succes
    private String erreurMessage; 



}
