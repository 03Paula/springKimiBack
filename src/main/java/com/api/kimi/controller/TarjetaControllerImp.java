package com.api.kimi.controller;


import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.error.TarjetaNotFoundException;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.model.Tarjeta;
import com.api.kimi.service.TarjetaService;
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
@Api(value = "tarjetas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarjetaControllerImp implements TarjetaController{
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping("/kimi/tarjeta")
    @ApiOperation(value = "Create a new credit card", notes = "Create a new credit card", httpMethod = "POST")
    // Create a new credit card
    public ResponseEntity<?> create(@RequestBody CrearTarjetaDTO crearTarjeta){
        log.info((" Creación de una nueva tarjeta"));

        return tarjetaService.save(crearTarjeta);
    }


    // Read a card
    @GetMapping("/kimi/tarjeta/{id}")
    @ApiOperation(value = "Get a credit card by its id", notes = "Get a credit card by its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long tarjetaId){
        log.info("Obtencion de una tarjeta por su id.");

        Optional<TarjetaDTO> oTarjeta = tarjetaService.findById(tarjetaId);

        if(!oTarjeta.isPresent()){
            throw new TarjetaNotFoundException(tarjetaId);
        }

        return ResponseEntity.ok(oTarjeta);
    }


    // Update a card
    @Override
    @PutMapping("/kimi/tarjeta/{id}")
    @ApiOperation(value = "Update a credit card", notes = "Update a credit card", httpMethod = "PUT")
    public Tarjeta updateTarjeta(@RequestBody CrearTarjetaDTO tarjetaDetails, @PathVariable Long id) {
        log.info("Actualización de una tarjeta por su id.");
        return tarjetaService.update(tarjetaDetails, id);
    }


    @DeleteMapping("/kimi/tarjeta/{id}")
    @ApiOperation(value = "Delete a credit card", notes = "Delete a credit card", httpMethod = "DELETE")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long tarjetaId) {
        log.info("Eliminanción de una tarjeta");

        if (!tarjetaService.findById(tarjetaId).isPresent()){
            throw new TarjetaNotFoundException(tarjetaId);
        }

        tarjetaService.deleteById(tarjetaId);
        return ResponseEntity.ok().build();
    }

    // Read all credit cards
    @GetMapping("/kimi/tarjetas")
    @ApiOperation(value = "Get all credit cards", notes = "Get all credit cards", httpMethod = "GET")
    @Override
    public List<TarjetaDTO> readAll() {
        log.info("Obtención de todas las tarjetas");

        return (List<TarjetaDTO>) tarjetaService.findAll();
    }

    // Read all credit cards by user.
    @GetMapping("/kimi/tarjetas/usuario/{id}")
    @ApiOperation(value = "Get all credit cards by user id", notes = "Get all credit cards by user id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> getCardByUser(@PathVariable Long id) {
        log.info("Obtencion de tarjetas por su usuario.");
        List<TarjetaDTO> oTarjeta = tarjetaService.findByUsuarioId(id);
        if (oTarjeta.isEmpty()){
            throw new UsuarioNotFoundException(id);
        }

        return ResponseEntity.ok(oTarjeta);
    }
}
