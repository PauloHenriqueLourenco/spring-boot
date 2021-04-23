package com.gclick.testefullstack.repositories.fakes;

import com.gclick.testefullstack.dtos.CreateClienteDTO;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import com.gclick.testefullstack.repositories.IClientesRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FakeClientesRepository implements IClientesRepository {

    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public List<Cliente> findAll() {
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        var findCliente = clientes
                .stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();

        return findCliente;
    }

    @Override
    public Cliente create(CreateClienteDTO clienteData) {
        var cliente = new Cliente();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Long lastId = 0L;

        if (this.clientes.size() > 0) {
            this.clientes.sort(Comparator.comparing(Cliente::getId).reversed());
            lastId = this.clientes.get(0).getId();
        }

        cliente = modelMapper.map(clienteData, Cliente.class);
        cliente.setId(lastId + 1);

        this.clientes.add(cliente);

        return cliente;
    }

    @Override
    public Cliente save(Cliente cliente) {
        var findIndex = IntStream.range(0, this.clientes.size())
                .filter(index -> cliente.getId().equals(this.clientes.get(index).getId()))
                .findFirst()
                .orElse(-1);

        var findCliente = this.clientes.get(findIndex);

        findCliente = cliente;

        return findCliente;
    }

    @Override
    public void deleteById(Long id) {
        this.clientes.removeIf(cliente -> cliente.getId().equals(id));
    }

    @Override
    public Page<Cliente> findAllPage(Pageable pageable) {
        return null;
    }
}
