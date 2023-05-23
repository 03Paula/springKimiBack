package com.api.kimi.controller;

import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.model.Tarjeta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface TarjetaController {
    public ResponseEntity<?> create(@RequestBody CrearTarjetaDTO tarjeta);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long tarjetaId);

    public Tarjeta updateTarjeta(@RequestBody CrearTarjetaDTO tarjetaDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long tarjetaId);

    public List<TarjetaDTO> readAll();

    public ResponseEntity<?> getCardByUser(@PathVariable Long id);

}
