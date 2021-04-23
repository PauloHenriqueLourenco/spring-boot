package com.gclick.testefullstack.infra.jpa.repositories;

import com.gclick.testefullstack.dtos.CreateClienteDTO;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import com.gclick.testefullstack.repositories.IClientesRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class ClientesRepository implements IClientesRepository {

    private final IJpaClientesRepository clientesRepository;

    @Autowired
    public ClientesRepository(IJpaClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return this.clientesRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return this.clientesRepository.findById(id);
    }

    @Override
    public Cliente create(CreateClienteDTO clienteData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        var cliente = modelMapper.map(clienteData, Cliente.class);

        return this.clientesRepository.save(cliente);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.clientesRepository.saveAndFlush(cliente);
    }

    @Override
    public void deleteById(Long id) {
        this.clientesRepository.deleteById(id);
    }

    @Override
    public Page<Cliente> findAllPage(Pageable pageable) {
        return this.clientesRepository.findAll(pageable);
    }
}
