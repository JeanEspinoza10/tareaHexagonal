package com.api.ms_espinoza_hexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empleado")
@Getter
@Setter

public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "nombre", nullable = true, length = 150)
    private String nombre;

    @Column(name = "apellido", nullable = true, length = 150)
    private String apellido;

    @Column(name = "edad", nullable = true)
    private int edad;

    @Column(name = "cargo", length = 100)
    private String cargo;

    @Column(name = "tipo_doc", nullable = true, length = 15)
    private String tipoDoc;

    @Column(name = "num_doc", nullable = true, length = 15)
    private String numDoc;

    @Column(name = "departamento", nullable = true, length = 100)
    private String departamento;

    @Column(name = "salario", nullable = true)
    private double salario;

    @Column(name = "telefono", nullable = true, length = 20)
    private String telefono;

    @Column(name = "correo", nullable = true, length = 100)
    private String correo;

    @Column(name = "estado", nullable = true)
    private boolean estado;

    @Column(name = "direccion", nullable = true, length = 200)
    private String direccion;

    @Column(name = "date_crea")
    private Timestamp dateCrea;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_update")
    private Timestamp dateUpdate;

    @Column(name = "usua_update", length = 45)
    private String usuaUpdate;

    @Column(name = "date_delete")
    private Timestamp dateDelete;

    @Column(name = "usua_delete", length = 45)
    private String usuaDelete;

}
