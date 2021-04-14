package com.gclick.testefullstack.infra.http.response;

import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListClientesResponse {

    private final List<Cliente> clientes;
}
