package com.api.kimi.controller;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.dto.usuario.CrearUsuarioDTO;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.error.EmailNotFoundException;
import com.api.kimi.model.Usuario;
import com.api.kimi.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioControllerImp implements UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/kimi/usuario")
    @ApiOperation(value = "Create a new user", notes = "Create a new user", httpMethod = "POST")
    // Create a new client
    public ResponseEntity<?> create(@RequestBody CrearUsuarioDTO crearCliente){
        log.info((" Creación de un nuevo usuario"));

        return usuarioService.save(crearCliente);
    }


    // Read a client
    @GetMapping("/kimi/usuario/{id}")
    @ApiOperation(value = "Get an user by their id", notes = "Get an user by their id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long clienteId){
        log.info("Obtencion de un cliente por su id.");

        Optional<UsuarioDTO> oCliente = usuarioService.findById(clienteId);

        if(!oCliente.isPresent()){
            throw new UsuarioNotFoundException(clienteId);
        }

        return ResponseEntity.ok(oCliente);
    }


    // Update a client
    @Override
    @PutMapping("/kimi/usuario/{id}")
    @ApiOperation(value = "Update an user", notes = "Update an user", httpMethod = "PUT")
    public Usuario updateCliente(@RequestBody CrearUsuarioDTO clienteDetails, @PathVariable Long id) {
        log.info("Actualización de un cliente por su id.");
        return usuarioService.update(clienteDetails, id);
    }


    // Delete a client
    @DeleteMapping("/kimi/usuario/{id}")
    @ApiOperation(value = "Delete an user", notes = "Delete an user", httpMethod = "DELETE")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long clienteId) {
        log.info("Eliminanción de un cliente");

        if (!usuarioService.findById(clienteId).isPresent()){
            throw new UsuarioNotFoundException(clienteId);
        }

        usuarioService.deleteById(clienteId);
        return ResponseEntity.ok().build();
    }

    // Read all clients
    @GetMapping("/kimi/usuarios")
    @ApiOperation(value = "Get all users", notes = "Get all users", httpMethod = "GET")
    @Override
    public List<UsuarioDTO> readAll() {
        log.info("Obtención de todos los clientes");

        return (List<UsuarioDTO>) usuarioService.findAll();
    }


    // Read a client by their email
    @GetMapping("/kimi/usuarios/email/{email}")
    @ApiOperation(value = "Get an user by their email", notes = "Get an user by their email", httpMethod = "GET")
    @Override
    public ResponseEntity<?> getClientByEmail(@PathVariable(value = "email") String email) {
        log.info("Obtención de un cliente por su email");
        Optional<UsuarioDTO> oCliente = usuarioService.findClientByEmail(email);
        if(!oCliente.isPresent()){
            throw new EmailNotFoundException(email);
        }

        return ResponseEntity.ok(oCliente);
    }

}
