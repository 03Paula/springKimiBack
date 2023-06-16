package com.api.kimi.controller.impl;

import com.api.kimi.controller.CarritoController;
import com.api.kimi.dto.carrito.CarritoDTO;
import com.api.kimi.dto.carrito.CrearCarritoDTO;
import com.api.kimi.error.Carrito.CarritoNotFoundException;
import com.api.kimi.error.Usuario.UsuarioNotFoundException;
import com.api.kimi.model.Carrito;
import com.api.kimi.service.CarritoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "carrito", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarritoControllerImp implements CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/kimi/carrito")
    @ApiOperation(value = "Create a new cart", notes = "Create a new cart", httpMethod = "POST")
    // Create a new cart
    public ResponseEntity<?> create(@RequestBody CrearCarritoDTO crearCarrito){
        log.info((" Creación de un nuevo carrito"));

        return carritoService.save(crearCarrito);
    }


    // Read an cart
    @GetMapping("/kimi/carrito/:{id}")
    @ApiOperation(value = "Get an cart by its id", notes = "Get an cart by its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long carritoId){
        log.info("Obtencion de un carrito por su id.");

        Optional<CarritoDTO> oCarrito = carritoService.findById(carritoId);

        if(!oCarrito.isPresent()){
            throw new CarritoNotFoundException(carritoId);
        }

        return ResponseEntity.ok(oCarrito);
    }


    // Update a cart
    @Override
    @PutMapping("/kimi/carrito/:{id}")
    @ApiOperation(value = "Update a cart by its id", notes = "Update a cart by its id", httpMethod = "PUT")
    public Carrito updateCarrito(@RequestBody CrearCarritoDTO carritoDetails, @PathVariable Long id) {
        log.info("Actualización de un carrito por su id.");
        return carritoService.update(carritoDetails, id);
    }


    @DeleteMapping("/kimi/carrito/{id}")
    @ApiOperation(value = "Delete a cart by its id", notes = "Delete a cart by its id", httpMethod = "DELETE")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long carritoId) {
        log.info("Eliminanción de un carrito");

        if (!carritoService.findById(carritoId).isPresent()){
            throw new CarritoNotFoundException(carritoId);
        }

        carritoService.deleteById(carritoId);
        return ResponseEntity.ok().build();
    }

    // Read all carts
    @GetMapping("/kimi/carritos")
    @ApiOperation(value = "Get all carts", notes = "Get all carts", httpMethod = "GET")
    @Override
    public List<CarritoDTO> readAll() {
        log.info("Obtención de todos los carritos");

        return (List<CarritoDTO>) carritoService.findAll();
    }

    @GetMapping("/kimi/carrito/usuario/{id}")
    @ApiOperation(value = "Get a cart by its user id", notes = "Get a cart by its user id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> getCartByUser(Long id) {
        log.info("Obtencion de un carrito por un usuario.");
        List<CarritoDTO> oCarrito = carritoService.findByUsuarioId(id);
        if (oCarrito.isEmpty()){
            throw new UsuarioNotFoundException(id);
        }
        return ResponseEntity.ok(oCarrito);
    }

}
