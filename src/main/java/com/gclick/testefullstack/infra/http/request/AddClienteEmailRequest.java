package com.gclick.testefullstack.infra.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddClienteEmailRequest {

    private final Long cliente_id;
    private final String categoria;
    private final String nome;
    private final String email;

}
