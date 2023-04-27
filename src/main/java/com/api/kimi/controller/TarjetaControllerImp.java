package com.api.kimi.controller;


import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.error.TarjetaNotFoundException;
import com.api.kimi.model.Tarjeta;
import com.api.kimi.service.TarjetaService;
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
public class TarjetaControllerImp implements TarjetaController{
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping("/kimi/tarjeta")
    // Create a new card
    public ResponseEntity<?> create(@RequestBody CrearTarjetaDTO crearTarjeta){
        log.info((" Creación de una nueva tarjeta"));

        return tarjetaService.save(crearTarjeta);
    }


    // Read a card
    @GetMapping("/kimi/tarjeta/{id}")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long tarjetaId){
        log.info("Obtencion de una tarjeta por su id.");

        Optional<Tarjeta> oTarjeta = tarjetaService.findById(tarjetaId);

        if(!oTarjeta.isPresent()){
            throw new TarjetaNotFoundException(tarjetaId);
        }

        return ResponseEntity.ok(oTarjeta);
    }


    // Update a card
    @Override
    @PutMapping("/kimi/tarjeta/{id}")
    public Tarjeta updateTarjeta(@RequestBody CrearTarjetaDTO tarjetaDetails, @PathVariable Long id) {
        log.info("Actualización de una tarjeta por su id.");
        return tarjetaService.update(tarjetaDetails, id);
    }


    @DeleteMapping("/kimi/tarjeta/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long tarjetaId) {
        log.info("Eliminanción de una tarjeta");

        if (!tarjetaService.findById(tarjetaId).isPresent()){
            throw new TarjetaNotFoundException(tarjetaId);
        }

        tarjetaService.deleteById(tarjetaId);
        return ResponseEntity.ok().build();
    }

    // Read all address
    @GetMapping("/kimi/tarjetas")
    @Override
    public List<TarjetaDTO> readAll() {
        log.info("Obtención de todas las tarjetas");

        return (List<TarjetaDTO>) tarjetaService.findAll();
    }
}
