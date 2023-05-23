package com.api.kimi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "direcciones")
public class Direccion implements Serializable {
    private static final long serialVersionUID = -1240008461285247105L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(length = 100, nullable = false)
    private String nombre_calle;

    @Column(length = 20 , nullable = true)
    private String piso;

    @Column(length = 5, nullable = false)
    private Integer codigo_postal;

    @Column(length = 30, nullable = false)
    private String pais;

    @Column(length = 30, nullable = false)
    private String ciudad;
    @Column(length = 30, nullable = false)
    private String provincia;

}
