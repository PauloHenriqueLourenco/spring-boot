package com.gclick.testefullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateClienteDTO {

    private final Long clienteId;
    private final String inscricao;
    private final String nome;
    private final String apelido;

}
