package com.api.kimi.dto.tarjeta;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TarjetaDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long usuarioId;
    @NotNull
    private String titular;
    @NotNull
    private String n_tarjeta;
    @NotNull
    @JsonFormat(pattern="MM/yy", shape = JsonFormat.Shape.STRING)
    private String vencimiento;
    @NotNull
    private Integer cvv;
}
