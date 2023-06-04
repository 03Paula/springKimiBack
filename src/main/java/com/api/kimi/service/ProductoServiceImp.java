package com.api.kimi.service;

import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.dto.producto.CrearProductoDTO;
import com.api.kimi.dto.converter.ProductoDTOConverter;
import com.api.kimi.error.ProductoNotFoundException;
import com.api.kimi.model.Producto;
import com.api.kimi.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoServiceImp implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;
    private final ProductoDTOConverter productoDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ProductoDTO> findAll(){
        List<ProductoDTO> dtoList = productoRepository.findAll().stream().map(productoDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id) {
        return  productoRepository.findById(id).map(productoDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearProductoDTO crearProducto) {
        Producto nProducto = new Producto();
        nProducto.setCategoria(crearProducto.getCategoria());
        nProducto.setNombre(crearProducto.getNombre());
        nProducto.setPrecio(crearProducto.getPrecio());
        nProducto.setCantidad(crearProducto.getCantidad());
        nProducto.setImagen(crearProducto.getImagen());
        nProducto.setDescripcion(crearProducto.getDescripcion());
        nProducto.setTamanio(crearProducto.getTamanio());
        nProducto.setAutor(crearProducto.getAutor());
        nProducto.setGeneroSerie(crearProducto.getGeneroSerie());
        nProducto.setFormatoColeccion(crearProducto.getFormatoColeccion());
        nProducto.setPaginas(crearProducto.getPaginas());
        nProducto.setFechaSalida(crearProducto.getFechaSalida());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepository.save(nProducto));
    }

    @Override
    public Producto update(CrearProductoDTO modProducto ,Long id) {
        return productoRepository.findById(id).map(p -> {
            p.setCategoria(modProducto.getCategoria());
            p.setNombre(modProducto.getNombre());
            p.setPrecio(modProducto.getPrecio());
            p.setCantidad(modProducto.getCantidad());
            p.setImagen(modProducto.getImagen());
            p.setDescripcion(modProducto.getDescripcion());
            p.setTamanio(modProducto.getTamanio());
            p.setAutor(modProducto.getAutor());
            p.setGeneroSerie(modProducto.getGeneroSerie());
            p.setFormatoColeccion(modProducto.getFormatoColeccion());
            p.setPaginas(modProducto.getPaginas());
            p.setFechaSalida(modProducto.getFechaSalida());
            return productoRepository.save(p);
        }).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Page<ProductoDTO> findAllPaginated(Pageable pageable) {
        Page<Producto> pageProducto = productoRepository.findAll(pageable);
        List<Producto> productos = pageProducto.getContent();
        List<ProductoDTO> productosPaginados = new ArrayList<>();
        productosPaginados = productoRepository.findAll().stream().map(productoDTOConverter::convertToDTO).collect(Collectors.toList());
        return new PageImpl<>(productosPaginados, pageable , productosPaginados.size());
    }
}
