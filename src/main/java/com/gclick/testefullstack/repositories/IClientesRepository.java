package com.gclick.testefullstack.repositories;

import com.gclick.testefullstack.dtos.CreateClienteDTO;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClientesRepository {

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Cliente create(CreateClienteDTO clienteData);

    Cliente save(Cliente cliente);

    void deleteById(Long id);

    Page<Cliente> findAllPage(Pageable pageable);

}
