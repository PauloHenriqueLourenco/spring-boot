package com.gclick.testefullstack.infra.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateClienteRequest {

    private final Long cliente_id;
    private final String inscricao;
    private final String nome;
    private final String apelido;

}
