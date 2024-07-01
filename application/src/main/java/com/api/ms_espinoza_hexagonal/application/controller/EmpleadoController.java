package com.api.ms_espinoza_hexagonal.application.controller;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.api.ms_espinoza_hexagonal.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDTO> crear(@RequestBody RequestEmpleado empleado){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.crearEmpleadoIn(empleado));
    }

}
