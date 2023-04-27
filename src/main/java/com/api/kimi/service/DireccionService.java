package com.api.kimi.service;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.cliente.CrearClienteDTO;
import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Cliente;
import com.api.kimi.model.Direccion;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface DireccionService {
    public Iterable<DireccionDTO> findAll();

    public Optional<Direccion> findById(Long id);

    public ResponseEntity<?> save(CrearDireccionDTO crearDireccion);

    public Direccion update(CrearDireccionDTO modDireccion, Long id);

    public void deleteById(Long id);
}
