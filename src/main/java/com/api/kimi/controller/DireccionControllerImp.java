package com.api.kimi.controller;

import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.model.Direccion;
import com.api.kimi.service.DireccionService;
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
public class DireccionControllerImp implements DireccionController{
    @Autowired
    private DireccionService direccionService;

    @PostMapping("/kimi/direccion")
    // Create a new address
    public ResponseEntity<?> create(@RequestBody CrearDireccionDTO crearDireccion){
        log.info((" Creación de una nueva dirección"));

        return direccionService.save(crearDireccion);
    }


    // Read an address
    @GetMapping("/kimi/direccion/{id}")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long direccionId){
        log.info("Obtencion de una dirección por su id.");

        Optional<Direccion> oDireccion = direccionService.findById(direccionId);

        if(!oDireccion.isPresent()){
            throw new DireccionNotFoundException(direccionId);
        }

        return ResponseEntity.ok(oDireccion);
    }


    // Update a client
    @Override
    @PutMapping("/kimi/direccion/{id}")
    public Direccion updateDireccion(@RequestBody CrearDireccionDTO direccionDetails, @PathVariable Long id) {
        log.info("Actualización de una dirección por su id.");
        return direccionService.update(direccionDetails, id);
    }


    @DeleteMapping("/kimi/direccion/{id}")
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
    @Override
    public List<DireccionDTO> readAll() {
        log.info("Obtención de todas las direcciones");

        return (List<DireccionDTO>) direccionService.findAll();
    }
}
