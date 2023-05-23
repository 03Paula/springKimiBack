package com.api.kimi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany
    @JoinColumn(name = "id_productos", referencedColumnName = "id")
    private List<Producto> productos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecha_pedido;

    public Double precioTotal() {
        double sum = 0D;
        List<Producto> productos = getProductos();
        for (Producto p : productos) {
            sum += p.getPrecio();
        }
        return sum;
    }

    @Column(length = 30)
    private String estado_pedido;
}
