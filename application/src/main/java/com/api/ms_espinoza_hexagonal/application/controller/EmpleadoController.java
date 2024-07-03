package com.api.ms_espinoza_hexagonal.application.controller;


import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;
import com.api.ms_espinoza_hexagonal.domain.ports.in.EmpleadoServiceIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase> crear(@RequestBody RequestEmpleado empleado){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.crearEmpleadoIn(empleado));
    }

    @GetMapping("/buscar/")
    public ResponseEntity<ResponseBase> buscar(@RequestParam String documento){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.buscarEmpleadoIn(documento));
    }
}
