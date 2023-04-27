package com.api.kimi.dto.tarjeta;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TarjetaDTO {
    private Long id;
    private String titular;
    private String n_tarjeta;
    private Date vencimiento;
    private Integer cvv;
}
