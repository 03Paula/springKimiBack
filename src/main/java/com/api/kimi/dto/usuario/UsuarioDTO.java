package com.api.kimi.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String contrasenia;

    private String rol;

}
