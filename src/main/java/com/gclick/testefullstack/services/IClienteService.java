package com.gclick.testefullstack.services;

import com.gclick.testefullstack.dtos.AddEmailDTO;
import com.gclick.testefullstack.dtos.RemoveEmailDTO;
import com.gclick.testefullstack.dtos.UpdateClienteDTO;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> listClientes();

    Page<Cliente> listClientesPage(Pageable pageable);

    Optional<Cliente> showCliente(Long id);

    Cliente updateCliente(UpdateClienteDTO updateClienteDTO);

    void deleteCliente(Long id);

    Cliente addEmail(AddEmailDTO addEmailDTO);

    Cliente removeEmail(RemoveEmailDTO removeEmailDTO);

}
