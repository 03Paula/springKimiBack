package com.api.kimi.repository;

import com.api.kimi.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioId(Long id_usuario);

}
