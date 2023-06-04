package com.api.kimi.controller;

import com.api.kimi.dto.pedido.CrearPedidoDTO;
import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PedidoController {
    public ResponseEntity<?> create(@RequestBody CrearPedidoDTO pedido);
    public ResponseEntity<?> read(@PathVariable(value = "id") Long pedidoId);
    public Pedido updatePedido(@RequestBody CrearPedidoDTO pedidoDetails, @PathVariable Long id);
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long pedidoId);
    public List<PedidoDTO> readAll();
    public ResponseEntity<?> getOrderByUser(@PathVariable Long id);

}
