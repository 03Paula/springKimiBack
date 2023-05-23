package com.api.kimi.controller;

import com.api.kimi.dto.producto.CrearProductoDTO;
import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.model.Producto;
import com.api.kimi.service.ProductoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductoControllerImp implements ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("/kimi/producto")
    @ApiOperation(value = "Create a new product", notes = "Create a new product", httpMethod = "POST")
    // Create a new product
    public ResponseEntity<?> create(@RequestBody CrearProductoDTO crearProducto){
        log.info((" Creación de un nuevo producto"));

        return productoService.save(crearProducto);
    }


    // Read a product
    @GetMapping("/kimi/producto/{id}")
    @ApiOperation(value = "Get a product by its id", notes = "Get a product by its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long productoId){
        log.info("Obtencion de un producto por su id.");

        Optional<Producto> oProducto = productoService.findById(productoId);

        if(!oProducto.isPresent()){
            throw new UsuarioNotFoundException(productoId);
        }

        return ResponseEntity.ok(oProducto);
    }


    // Update a product
    @Override
    @PutMapping("/kimi/producto/{id}")
    @ApiOperation(value = "Update a product", notes = "Update a product ", httpMethod = "PUT")
    public Producto updateProducto(@RequestBody CrearProductoDTO productoDetails, @PathVariable Long id) {
        log.info("Actualización de un producto por su id.");
        return productoService.update(productoDetails, id);
    }


    @DeleteMapping("/kimi/producto/{id}")
    @ApiOperation(value = "Delete a product", notes = "Delete a product", httpMethod = "DELETE")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long productoId) {
        log.info("Eliminanción de un producto por su id.");

        if (!productoService.findById(productoId).isPresent()){
            throw new UsuarioNotFoundException(productoId);
        }

        productoService.deleteById(productoId);
        return ResponseEntity.ok().build();
    }

    // Read all products
    @GetMapping("/kimi/productos")
    @ApiOperation(value = "Get all products", notes = "Get all products", httpMethod = "GET")
    @Override
    public List<ProductoDTO> readAll() {
        log.info("Obtención de todos los productos");

        return (List<ProductoDTO>) productoService.findAll();
    }

    @Override
    @GetMapping("/kimi/productos/page")
    public ResponseEntity<Page<ProductoDTO>> readAllPageable(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        try{
            Page<ProductoDTO> pageProd= productoService.findAllPaginated(pageable);
            return new ResponseEntity<>(pageProd, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
