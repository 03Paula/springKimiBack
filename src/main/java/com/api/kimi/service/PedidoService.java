package com.api.kimi.service;

import com.api.kimi.dto.pedido.CrearPedidoDTO;
import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.model.Pedido;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public Iterable<PedidoDTO> findAll();

    public Optional<PedidoDTO> findById(Long id);

    public ResponseEntity<?> save(CrearPedidoDTO crearPedido);

    public Pedido update(CrearPedidoDTO modPedido, Long id);

    public void deleteById(Long id);

    public List<PedidoDTO> findByUsuarioId(Long id_cliente);
}
