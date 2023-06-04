package com.api.kimi.controller;

import com.api.kimi.dto.carrito.CarritoDTO;
import com.api.kimi.dto.carrito.CrearCarritoDTO;
import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Carrito;
import com.api.kimi.model.Direccion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CarritoController {
    public ResponseEntity<?> create(@RequestBody CrearCarritoDTO carrito);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long carritoId);

    public Carrito updateCarrito(@RequestBody CrearCarritoDTO carritoDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long carritoId);

    public List<CarritoDTO> readAll();

    public ResponseEntity<?> getCartByUser(@PathVariable Long id);
}
