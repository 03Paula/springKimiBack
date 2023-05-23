package com.api.kimi.controller;

import com.api.kimi.dto.producto.CrearProductoDTO;
import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductoController {
    public ResponseEntity<?> create(@RequestBody CrearProductoDTO producto);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long productoId);

    public Producto updateProducto(@RequestBody CrearProductoDTO productoDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long productoId);

    public List<ProductoDTO> readAll();

    public ResponseEntity<Page<ProductoDTO>> readAllPageable(Pageable pageable);
}
