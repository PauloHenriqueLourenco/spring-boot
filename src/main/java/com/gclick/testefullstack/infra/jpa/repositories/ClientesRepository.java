package com.gclick.testefullstack.infra.jpa.repositories;

import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import com.gclick.testefullstack.repositories.IClientesRepository;
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
