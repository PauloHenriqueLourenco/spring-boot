package com.gclick.testefullstack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateClienteDTO {

    private String inscricao;
    private String nome;
    private String apelido;

}
