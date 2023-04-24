package com.api.kimi.dto.cliente;

import com.api.kimi.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO{
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String contrasenia;


}
