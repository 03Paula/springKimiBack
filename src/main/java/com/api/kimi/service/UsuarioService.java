package com.api.kimi.service;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.dto.usuario.CrearUsuarioDTO;
import com.api.kimi.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService {

    public Iterable<UsuarioDTO> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public ResponseEntity<?> save(CrearUsuarioDTO crearCliente);

    public Usuario update(CrearUsuarioDTO modCliente, Long id);

    public void deleteById(Long id);

    public Optional<UsuarioDTO> findClientByEmail(String email);

}
