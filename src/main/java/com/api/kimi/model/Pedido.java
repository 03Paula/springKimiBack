package com.api.kimi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = -3495391279414315859L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pedidos_productos", joinColumns = @JoinColumn(name = "pedido_id"),
    inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecha_pedido;

    public Double precioTotal;

    @Column(length = 30)
    private String estado_pedido;
}
