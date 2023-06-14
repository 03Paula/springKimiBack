package com.api.kimi.controller.impl;

import com.api.kimi.controller.ProductoController;
import com.api.kimi.dto.producto.CrearProductoDTO;
import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.error.Producto.AuthorNotFoundException;
import com.api.kimi.error.Producto.CategoryNotFoundException;
import com.api.kimi.error.Producto.ProductoNombreNotFoundException;
import com.api.kimi.error.Producto.ProductoNotFoundException;
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
    @GetMapping("/kimi/producto/:{id}")
    @ApiOperation(value = "Get a product by its id", notes = "Get a product by its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long productoId){
        log.info("Obtencion de un producto por su id.");

        Optional<ProductoDTO> oProducto = productoService.findById(productoId);

        if(!oProducto.isPresent()){
            throw new ProductoNotFoundException(productoId);
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
            throw new ProductoNotFoundException(productoId);
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
    @ApiOperation(value = "Get all products paginated", notes = "Get all products paginated", httpMethod = "GET", responseContainer = "List")
    public ResponseEntity<Page<ProductoDTO>> readAllPageable(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        try{
            Page<ProductoDTO> pageProd= productoService.findAllPaginated(pageable);
            return new ResponseEntity<>(pageProd, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Read a product by its name.
    @Override
    @GetMapping("/kimi/producto/nombre/{nombre}")
    @ApiOperation(value = "Get all products that contains the name", notes = "Get all products that contains the name", httpMethod = "GET")
    public ResponseEntity<?> getProductByName(@PathVariable(value = "nombre") String nombre) {
        log.info("Obtención de producto/s por su nombre");
        List<ProductoDTO> oProducto = productoService.findByNombreLike(nombre);
        if (!oProducto.isEmpty()){
            return ResponseEntity.ok(oProducto);
        }else{
            throw new ProductoNombreNotFoundException(nombre);
        }
    }

    @Override
    @GetMapping("/kimi/producto/categoria/{categoria}")
    @ApiOperation(value = "Get all products by category", notes = "Get all products by category", httpMethod = "GET")
    public ResponseEntity<?> getProductByCategory(@PathVariable(value = "categoria") String categoria) {
        log.info("Obtención de producto/s por su categoria");
        List<ProductoDTO> oProducto = productoService.findbyCategoriaLike(categoria);
        if (!oProducto.isEmpty()){
            return ResponseEntity.ok(oProducto);
        }else {
            throw new CategoryNotFoundException(categoria);
        }
    }

    @Override
    @GetMapping("/kimi/producto/autor/{autor}")
    @ApiOperation(value = "Get all products by author", notes = "Get all products by author", httpMethod = "GET")
    public ResponseEntity<?> getProductByAutor(String autor) {
        log.info("Obtención de producto/s por su autor");
        List<ProductoDTO> oProducto = productoService.findbyAutorLike(autor);
        if (!oProducto.isEmpty()) {
            return ResponseEntity.ok(oProducto);
        }else {
            throw new AuthorNotFoundException(autor);
        }
    }

    @Override
    @GetMapping("/kimi/producto/genero/{genero}")
    @ApiOperation(value = "Get all products by genre", notes = "Get all products by genre", httpMethod = "GET")
    public ResponseEntity<?> getProductByGenero(String genero) {
        log.info("Obtención de producto/s por su genero");
        List<ProductoDTO> oProducto = productoService.findbyGeneroLike(genero);
        if (!oProducto.isEmpty()) {
            return ResponseEntity.ok(oProducto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
