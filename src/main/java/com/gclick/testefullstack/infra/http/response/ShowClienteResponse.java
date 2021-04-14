package com.gclick.testefullstack.infra.http.response;

import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowClienteResponse {

    private final Cliente cliente;
}
