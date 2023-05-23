package com.api.kimi.service;

import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.model.Direccion;
import com.api.kimi.model.Tarjeta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TarjetaService {
    public Iterable<TarjetaDTO> findAll();

    public Optional<TarjetaDTO> findById(Long id);

    public ResponseEntity<?> save(CrearTarjetaDTO crearTarjeta);

    public Tarjeta update(CrearTarjetaDTO modTarjeta, Long id);

    public void deleteById(Long id);

    public List<TarjetaDTO> findByUsuarioId(Long id_cliente);

}
