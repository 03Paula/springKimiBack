package com.api.kimi.service;

import com.api.kimi.dto.carrito.CarritoDTO;
import com.api.kimi.dto.carrito.CrearCarritoDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Carrito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarritoService {
    public Iterable<CarritoDTO> findAll();

    public Optional<CarritoDTO> findById(Long id);

    public ResponseEntity<?> save(CrearCarritoDTO crearCarrito);

    public Carrito update(CrearCarritoDTO modCarrito, Long id);

    public void deleteById(Long id);

    public List<CarritoDTO> findByUsuarioId(Long id_usuario);

}
