package com.api.kimi.controller;

import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.error.DireccionUsuarioNotFoundException;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.model.Direccion;
import com.api.kimi.service.DireccionService;
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
@Api(value = "direcciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class DireccionControllerImp implements DireccionController{
    @Autowired
    private DireccionService direccionService;

    @PostMapping("/kimi/direccion")
    @ApiOperation(value = "Create a new address", notes = "Create a new address", httpMethod = "POST")
    // Create a new address
    public ResponseEntity<?> create(@RequestBody CrearDireccionDTO crearDireccion){
        log.info((" Creación de una nueva dirección"));

        return direccionService.save(crearDireccion);
    }


    // Read an address
    @GetMapping("/kimi/direccion/{id}")
    @ApiOperation(value = "Get an address by its id", notes = "Get an address by its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long direccionId){
        log.info("Obtencion de una dirección por su id.");

        Optional<DireccionDTO> oDireccion = direccionService.findById(direccionId);

        if(!oDireccion.isPresent()){
            throw new DireccionNotFoundException(direccionId);
        }

        return ResponseEntity.ok(oDireccion);
    }


    // Update an address
    @Override
    @PutMapping("/kimi/direccion/{id}")
    @ApiOperation(value = "Update an address by its id", notes = "Update an address by its id", httpMethod = "PUT")
    public Direccion updateDireccion(@RequestBody CrearDireccionDTO direccionDetails, @PathVariable Long id) {
        log.info("Actualización de una dirección por su id.");
        return direccionService.update(direccionDetails, id);
    }


    @DeleteMapping("/kimi/direccion/{id}")
    @ApiOperation(value = "Delete an address by its id", notes = "Delete an address by its id", httpMethod = "DELETE")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long direccionId) {
        log.info("Eliminanción de una dirección");

        if (!direccionService.findById(direccionId).isPresent()){
            throw new DireccionNotFoundException(direccionId);
        }

        direccionService.deleteById(direccionId);
        return ResponseEntity.ok().build();
    }

    // Read all address
    @GetMapping("/kimi/direcciones")
    @ApiOperation(value = "Get all addresses", notes = "Get all addresses", httpMethod = "GET")
    @Override
    public List<DireccionDTO> readAll() {
        log.info("Obtención de todas las direcciones");

        return (List<DireccionDTO>) direccionService.findAll();
    }

    @GetMapping("/kimi/direcciones/usuario/{id}")
    @ApiOperation(value = "Get an address by its user id", notes = "Get an address by its user id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> getDirectionByUser(Long id) {
        log.info("Obtencion de direcciones por un usuario.");
        List<DireccionDTO> oDireccion = direccionService.findByUsuarioId(id);
        if (oDireccion.isEmpty()){
            throw new DireccionUsuarioNotFoundException(id);
        }
        return ResponseEntity.ok(oDireccion);
    }

}
