package com.api.kimi.dto.direccion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearDireccionDTO {
    private Long usuarioId;
    private String nombre_calle;
    private String piso;
    private Integer codigo_postal;
    private String pais;
    private String ciudad;
    private String provincia;
}
