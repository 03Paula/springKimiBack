package com.api.kimi.repository;

import com.api.kimi.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    List<Tarjeta> findByUsuarioId(Long id_cliente);

}
