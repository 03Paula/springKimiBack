package com.api.kimi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @Column(length = 100, nullable = false)
    private String titular;

    @Column(length = 30, nullable = false)
    private String n_tarjeta;
    @Column(nullable = false)
    @JsonFormat(pattern="MM/yy", shape = JsonFormat.Shape.STRING)
    private String vencimiento;
    @Column(length = 3, nullable = false)
    private Integer cvv;
}
