package com.api.kimi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarjetas")
public class Tarjeta {

    private static final long serialVersionUID = 1288773992441069942L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String titular;

    @Column(length = 30, nullable = false)
    private String n_tarjeta;
    @Column(nullable = false)
    private Date vencimiento;
    @Column(length = 3, nullable = false)
    private Integer cvv;
}
