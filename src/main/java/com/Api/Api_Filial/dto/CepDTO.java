package com.Api.Api_Filial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
}
