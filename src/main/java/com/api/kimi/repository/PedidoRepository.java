package com.api.kimi.repository;

import com.api.kimi.model.Pedido;
import com.api.kimi.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioId(Long id_cliente);

}
