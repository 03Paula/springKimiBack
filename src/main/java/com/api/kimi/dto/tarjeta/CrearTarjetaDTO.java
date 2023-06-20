package com.api.kimi.dto.tarjeta;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CrearTarjetaDTO {
    private String titular;
    private Long usuarioId;
    private String n_tarjeta;
    @JsonFormat(pattern="MM/yy", shape = JsonFormat.Shape.STRING)
    private String vencimiento;
    private Integer cvv;
}
