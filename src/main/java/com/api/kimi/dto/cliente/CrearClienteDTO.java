package com.api.kimi.dto.cliente;

import com.api.kimi.model.Cliente;
import com.api.kimi.model.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrearClienteDTO{
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String contrasenia;
    private List<Direccion> direcciones;
}
