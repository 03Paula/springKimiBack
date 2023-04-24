package com.api.kimi.controller;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.cliente.CrearClienteDTO;
import com.api.kimi.error.ClienteNotFoundException;
import com.api.kimi.model.Cliente;
import com.api.kimi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClienteControllerImp implements ClienteController{
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/kimi/usuario")
    // Create a new client
    public ResponseEntity<?> create(@RequestBody CrearClienteDTO crearCliente){
        log.info((" Creación de un nuevo usuario"));

        return clienteService.save(crearCliente);
    }


    // Read a client
    @GetMapping("/kimi/usuario/{id}")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long clienteId){
        log.info("Obtencion de un cliente por su id.");

        Optional<Cliente> oCliente = clienteService.findById(clienteId);

        if(!oCliente.isPresent()){
            throw new ClienteNotFoundException(clienteId);
        }

        return ResponseEntity.ok(oCliente);
    }


    // Update a client
    @Override
    @PutMapping("/kimi/usuario/{id}")
    public Cliente updateCliente(@RequestBody CrearClienteDTO clienteDetails, @PathVariable Long id) {
        log.info("Actualización de un cliente por su id.");
        return clienteService.update(clienteDetails, id);
    }


    @DeleteMapping("/kimi/usuario/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long clienteId) {
        log.info("Eliminanción de un cliente");

        if (!clienteService.findById(clienteId).isPresent()){
            throw new ClienteNotFoundException(clienteId);
        }

        clienteService.deleteById(clienteId);
        return ResponseEntity.ok().build();
    }

    // Read all clients
    @GetMapping("/kimi/usuarios")
    @Override
    public List<ClienteDTO> readAll() {
        log.info("Obtención de todos los clientes");

        return (List<ClienteDTO>) clienteService.findAll();
    }


}
