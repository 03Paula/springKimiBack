package com.api.kimi.service;

import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.model.Direccion;
import com.api.kimi.model.Tarjeta;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TarjetaService {
    public Iterable<TarjetaDTO> findAll();

    public Optional<Tarjeta> findById(Long id);

    public ResponseEntity<?> save(CrearTarjetaDTO crearTarjeta);

    public Tarjeta update(CrearTarjetaDTO modTarjeta, Long id);

    public void deleteById(Long id);
}
