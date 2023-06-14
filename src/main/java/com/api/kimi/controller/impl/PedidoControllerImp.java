package com.api.kimi.controller.impl;

import com.api.kimi.controller.PedidoController;
import com.api.kimi.dto.pedido.CrearPedidoDTO;
import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.error.Pedido.PedidoNotFoundException;
import com.api.kimi.error.Usuario.UsuarioNotFoundException;
import com.api.kimi.model.Pedido;
import com.api.kimi.service.PedidoService;
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
@Api(value = "pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoControllerImp implements PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @PostMapping("/kimi/pedido")
    @ApiOperation(value = "Create a new order", notes = "Create a new order", httpMethod = "POST")
    // Create a new order
    public ResponseEntity<?> create(@RequestBody CrearPedidoDTO crearPedido){
        log.info(("Creación de un nuevo pedido"));
        return pedidoService.save(crearPedido);
    }


    // Read an order
    @GetMapping("/kimi/pedido/{id}")
    @ApiOperation(value = "Get an order its id", notes = "Get an order its id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> read(@PathVariable(value = "id") Long pedidoId){
        log.info("Obtencion de un pedido por su id.");

        Optional<PedidoDTO> oPedido = pedidoService.findById(pedidoId);

        if(!oPedido.isPresent()){
            throw new PedidoNotFoundException(pedidoId);
        }

        return ResponseEntity.ok(oPedido);
    }


    // Update an order
    @Override
    @PutMapping("/kimi/pedido/{id}")
    @ApiOperation(value = "Update an order", notes = "Update an order", httpMethod = "PUT")
    public Pedido updatePedido(@RequestBody CrearPedidoDTO pedidoDetails, @PathVariable Long id) {
        log.info("Actualización de un pedido por su id.");
        return pedidoService.update(pedidoDetails, id);
    }


    @DeleteMapping("/kimi/pedido/{id}")
    @Override
    @ApiOperation(value = "Delete an order", notes = "Delete an order", httpMethod = "DELETE")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long pedidoId) {
        log.info("Eliminanción de un pedido");

        if (!pedidoService.findById(pedidoId).isPresent()){
            throw new PedidoNotFoundException(pedidoId);
        }

        pedidoService.deleteById(pedidoId);
        return ResponseEntity.ok().build();
    }

    // Read all orders
    @GetMapping("/kimi/pedidos")
    @ApiOperation(value = "Get all orders", notes = "Get all orders", httpMethod = "GET")
    @Override
    public List<PedidoDTO> readAll() {
        log.info("Obtención de todos los clientes");

        return (List<PedidoDTO>) pedidoService.findAll();
    }

    // Read all orders by user.
    @GetMapping("/kimi/pedidos/usuario/{id}")
    @ApiOperation(value = "Get all orders by user id", notes = "Get all orders by user id", httpMethod = "GET")
    @Override
    public ResponseEntity<?> getOrderByUser(@PathVariable Long id) {
        log.info("Obtencion de pedidos por su usuario.");
        List<PedidoDTO> oPedido = pedidoService.findByUsuarioId(id);
        if (oPedido.isEmpty()){
            throw new UsuarioNotFoundException(id);
        }

        return ResponseEntity.ok(oPedido);
    }
}
