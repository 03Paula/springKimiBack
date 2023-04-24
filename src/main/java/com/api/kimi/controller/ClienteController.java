package com.api.kimi.controller;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.cliente.CrearClienteDTO;
import com.api.kimi.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClienteController {

    public ResponseEntity<?> create(@RequestBody CrearClienteDTO cliente);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long clienteId);

    public Cliente updateCliente(@RequestBody CrearClienteDTO clienteDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long clienteId);

    public List<ClienteDTO> readAll();


}
