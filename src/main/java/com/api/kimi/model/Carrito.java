package com.api.kimi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrito")
public class Carrito implements Serializable {
    private static final long serialVersionUID = 2527804297899700499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carrito_productos", joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Usuario usuario;

    private Integer cantidad = 1;

    private Double precioTotal;
}
