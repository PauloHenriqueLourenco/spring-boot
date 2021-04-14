package com.gclick.testefullstack.infra.http.controllers;

import com.gclick.testefullstack.dtos.AddEmailDTO;
import com.gclick.testefullstack.dtos.RemoveEmailDTO;
import com.gclick.testefullstack.dtos.UpdateClienteDTO;
import com.gclick.testefullstack.infra.http.request.AddClienteEmailRequest;
import com.gclick.testefullstack.infra.http.request.UpdateClienteRequest;
import com.gclick.testefullstack.infra.jpa.entities.Cliente;
import com.gclick.testefullstack.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public Page<Cliente> listClientesPage(Pageable pageable) {
        return this.clienteService.listClientesPage(pageable);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> listClientes(@PathVariable Long id) {
        final Optional<Cliente> optionalCliente = clienteService.showCliente(id);

        if (optionalCliente.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var cliente = optionalCliente.get();

        return ResponseEntity.ok(cliente);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> updateCliente(@RequestBody UpdateClienteRequest updateClienteRequest) {
        UpdateClienteDTO updateClienteDTO = new UpdateClienteDTO(
                updateClienteRequest.getCliente_id(),
                updateClienteRequest.getInscricao(),
                updateClienteRequest.getNome(),
                updateClienteRequest.getApelido()
        );

        var cliente = this.clienteService.updateCliente(updateClienteDTO);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCliente(@PathVariable Long id) {
        this.clienteService.deleteCliente(id);
    }

    @PostMapping(path = "/emails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> addClienteEmail(@RequestBody AddClienteEmailRequest addClienteEmailRequest) {
        AddEmailDTO addEmailDTO = new AddEmailDTO(
                addClienteEmailRequest.getCliente_id(),
                addClienteEmailRequest.getCategoria(),
                addClienteEmailRequest.getNome(),
                addClienteEmailRequest.getEmail()
        );

        var cliente = this.clienteService.addEmail(addEmailDTO);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(path = "/{clienteId}/emails/{emailId}")
    public ResponseEntity<Cliente> removeClienteEmail(@PathVariable Long clienteId, @PathVariable Long emailId) {
        RemoveEmailDTO removeEmailDTO = new RemoveEmailDTO(clienteId, emailId);

        var cliente = this.clienteService.removeEmail(removeEmailDTO);

        return ResponseEntity.ok(cliente);
    }
}
