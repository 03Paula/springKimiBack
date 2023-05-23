package com.api.kimi.repository;

import com.api.kimi.model.Direccion;
import com.api.kimi.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByUsuarioId(Long id_usuario);

}
