package com.api.kimi.controller;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.dto.usuario.CrearUsuarioDTO;
import com.api.kimi.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UsuarioController {

    public ResponseEntity<?> create(@RequestBody CrearUsuarioDTO cliente);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long clienteId);

    public Usuario updateCliente(@RequestBody CrearUsuarioDTO clienteDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long clienteId);

    public List<UsuarioDTO> readAll();

    public ResponseEntity<?> getClientByEmail(@PathVariable(value = "email") String email);
}
