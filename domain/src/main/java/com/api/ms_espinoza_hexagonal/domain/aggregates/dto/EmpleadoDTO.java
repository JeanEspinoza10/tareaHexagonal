package com.api.ms_espinoza_hexagonal.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter

public class EmpleadoDTO {
    private Long idPersona;
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUpdate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;
}