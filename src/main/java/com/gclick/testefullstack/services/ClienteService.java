package com.gclick.testefullstack.services;

import com.gclick.testefullstack.dtos.AddEmailDTO;
import com.gclick.testefullstack.dtos.RemoveEmailDTO;
import com.gclick.testefullstack.dtos.UpdateClienteDTO;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import com.gclick.testefullstack.infra.jpa.entities.Email;
import com.gclick.testefullstack.repositories.IClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final IClientesRepository clientesRepository;

    @Autowired
    public ClienteService(IClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Cliente> listClientes() {
        return this.clientesRepository.findAll();
    }

    @Override
    public Page<Cliente> listClientesPage(Pageable pageable) {
        return this.clientesRepository.findAllPage(pageable);
    }

    @Override
    public Optional<Cliente> showCliente(Long id) {
        return this.clientesRepository.findById(id);
    }

    @Override
    public Cliente updateCliente(UpdateClienteDTO updateClienteDTO) {
        final Optional<Cliente> optionalCliente = this.clientesRepository.findById(updateClienteDTO.getClienteId());

        if (optionalCliente.isEmpty()) {
            return null;
        }

        var cliente = optionalCliente.get();

        cliente.setInscricao(updateClienteDTO.getInscricao());
        cliente.setNome(updateClienteDTO.getNome());
        cliente.setApelido(updateClienteDTO.getApelido());

        return this.clientesRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        this.clientesRepository.deleteById(id);
    }

    @Override
    public Cliente addEmail(AddEmailDTO addEmailDTO) {
        final Optional<Cliente> optionalCliente = this.clientesRepository.findById(addEmailDTO.getClienteId());

        if (optionalCliente.isEmpty()) {
            return null;
        }

        var cliente = optionalCliente.get();
        Email email = new Email();

        email.setCliente(cliente);
        email.setCategoria(addEmailDTO.getCategoria());
        email.setNome(addEmailDTO.getNome());
        email.setEmail(addEmailDTO.getEmail());

        if (cliente.getEmails() == null) {
            cliente.setEmails(new ArrayList<>());
        }

        cliente.getEmails().add(email);

        return this.clientesRepository.save(cliente);
    }

    @Override
    public Cliente removeEmail(RemoveEmailDTO removeEmailDTO) {
        final Optional<Cliente> optionalCliente = this.clientesRepository.findById(removeEmailDTO.getClienteId());

        if (optionalCliente.isEmpty()) {
            return null;
        }

        var cliente = optionalCliente.get();

        cliente.getEmails().removeIf(email -> email.getId().equals(removeEmailDTO.getEmailId()));

        return this.clientesRepository.save(cliente);
    }
}
