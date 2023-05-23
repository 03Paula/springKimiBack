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
@Table(name = "clientes")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -5613302301208111473L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellidos;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100, unique = true)
    private String email;

    private String contrasenia;

    private String rol;


}
