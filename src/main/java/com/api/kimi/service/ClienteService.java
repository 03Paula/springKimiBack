package com.api.kimi.service;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.cliente.CrearClienteDTO;
import com.api.kimi.model.Cliente;
import com.api.kimi.response.response;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClienteService {

    public Iterable<ClienteDTO> findAll();

    public Optional<Cliente> findById(Long id);

    public ResponseEntity<?> save(CrearClienteDTO crearCliente);

    public Cliente update(CrearClienteDTO modCliente, Long id);

    public void deleteById(Long id);

}
