package com.api.ms_espinoza_hexagonal.domain.aggregates.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmpleado {

    @NotBlank(message = "Validar numero de documento")
    private String numeroDocumento;

    private String cargo;
    private int edad;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private String direccion;
}
