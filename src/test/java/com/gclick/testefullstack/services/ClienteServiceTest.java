package com.gclick.testefullstack.services;

import com.gclick.testefullstack.dtos.AddEmailDTO;
import com.gclick.testefullstack.dtos.CreateClienteDTO;
import com.gclick.testefullstack.dtos.UpdateClienteDTO;
import com.gclick.testefullstack.repositories.fakes.FakeClientesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    private FakeClientesRepository fakeClientesRepository;
    private ClienteService clienteService;

    @BeforeEach
    void beforeEach() {
        fakeClientesRepository = new FakeClientesRepository();
        clienteService = new ClienteService(fakeClientesRepository);
    }

    @Test
    void it_should_be_able_to_list_clientes() {
        CreateClienteDTO clienteData1 = new CreateClienteDTO(
                "23.654.789/0001-03",
                "Meu Cliente 1",
                "Meu Cliente 1 LTDA"
        );
        var cliente1 = this.fakeClientesRepository.create(clienteData1);

        CreateClienteDTO clienteData2 = new CreateClienteDTO(
                "24.654.789/0001-03",
                "Meu Cliente 2",
                "Meu Cliente 2 LTDA"
        );
        var cliente2 = this.fakeClientesRepository.create(clienteData2);

        var clientes = this.clienteService.listClientes();

        assertEquals(Arrays.asList(cliente1, cliente2), clientes);
    }

    @Test
    void it_should_be_able_to_show_the_cliente() {
        CreateClienteDTO clienteData = new CreateClienteDTO(
                "23.654.789/0001-03",
                "Meu Cliente",
                "Meu Cliente LTDA"
        );
        var cliente = this.fakeClientesRepository.create(clienteData);

        var foundCliente = this.clienteService.showCliente(cliente.getId());

        assertTrue(foundCliente.isPresent());
        assertEquals("23.654.789/0001-03", foundCliente.get().getInscricao());
        assertEquals("Meu Cliente", foundCliente.get().getNome());
        assertEquals("Meu Cliente LTDA", foundCliente.get().getApelido());
    }

    @Test
    void it_should_be_able_to_update_the_cliente() {
        CreateClienteDTO clienteData = new CreateClienteDTO(
                "23.654.789/0001-03",
                "Meu Cliente",
                "Meu Cliente LTDA"
        );
        var cliente = this.fakeClientesRepository.create(clienteData);

        UpdateClienteDTO updateClienteData = new UpdateClienteDTO(
                cliente.getId(),
                "22.222.222/0002-02",
                "Meu Cliente 2",
                "Meu Cliente 2 LTDA"
        );
        var updatedCliente = this.clienteService.updateCliente(updateClienteData);

        assertEquals("22.222.222/0002-02", updatedCliente.getInscricao());
        assertEquals("Meu Cliente 2", updatedCliente.getNome());
        assertEquals("Meu Cliente 2 LTDA", updatedCliente.getApelido());
    }

    @Test
    void it_should_be_able_to_delete_the_cliente() {
        var mockFakeClientesRepository = spy(fakeClientesRepository);
        var clienteServiceWithMockedInjection = new ClienteService(mockFakeClientesRepository);

        CreateClienteDTO clienteData = new CreateClienteDTO(
                "23.654.789/0001-03",
                "Meu Cliente",
                "Meu Cliente LTDA"
        );
        var cliente = mockFakeClientesRepository.create(clienteData);

        clienteServiceWithMockedInjection.deleteCliente(cliente.getId());

        verify(mockFakeClientesRepository, times(1)).deleteById(cliente.getId());
    }

    @Test
    void it_should_be_able_to_add_an_email_to_the_cliente() {
        CreateClienteDTO clienteData = new CreateClienteDTO(
                "23.654.789/0001-03",
                "Meu Cliente",
                "Meu Cliente LTDA"
        );
        var cliente = fakeClientesRepository.create(clienteData);

        AddEmailDTO addEmailData = new AddEmailDTO(
                cliente.getId(),
                "Financeiro",
                "João",
                "joao@example.com.br"
        );
        var updatedCliente = this.clienteService.addEmail(addEmailData);

        assertThat(updatedCliente.getEmails(), hasItem(allOf(
                hasProperty("categoria", is("Financeiro")),
                hasProperty("nome", is("João")),
                hasProperty("email", is("joao@example.com.br"))
        )));
    }

}