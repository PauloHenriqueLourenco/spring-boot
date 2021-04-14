package com.gclick.testefullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddEmailDTO {

    private final Long clienteId;
    private final String categoria;
    private final String nome;
    private final String email;

}
