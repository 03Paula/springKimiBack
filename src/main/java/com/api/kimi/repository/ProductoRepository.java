package com.api.kimi.repository;

import com.api.kimi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreLike(String nombre);

    List<Producto> findByCategoriaLike(String categoria);

    List<Producto> findByAutorLike(String autor);

    List<Producto> findByGeneroSerieLike(String genero);
}
