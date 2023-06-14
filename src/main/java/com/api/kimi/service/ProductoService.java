package com.api.kimi.service;


import com.api.kimi.dto.producto.CrearProductoDTO;
import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ProductoService {
    public Iterable<ProductoDTO> findAll();

    public Optional<ProductoDTO> findById(Long id);

    public ResponseEntity<?> save(CrearProductoDTO crearProducto);

    public Producto update(CrearProductoDTO modProducto, Long id);

    public void deleteById(Long id);

    public Page<ProductoDTO> findAllPaginated(Pageable pageable);

    public List<ProductoDTO> findByNombreLike(String nombre);

    public List<ProductoDTO> findbyCategoriaLike(String categoria);
    public List<ProductoDTO> findbyAutorLike(String autor);
    public List<ProductoDTO> findbyGeneroLike(String genero);

}
