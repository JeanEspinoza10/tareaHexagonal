package com.api.ms_espinoza_hexagonal.domain.impl;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;
import com.api.ms_espinoza_hexagonal.domain.ports.in.EmpleadoServiceIn;
import com.api.ms_espinoza_hexagonal.domain.ports.out.EmpleadoServiceOut;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;
    @Override
    public ResponseBase crearEmpleadoIn(RequestEmpleado empleado) {
        return empleadoServiceOut.crearEmpleadoOut(empleado);
    }

    @Override
    public ResponseBase buscarEmpleadoIn(String documento) {
        return empleadoServiceOut.buscarEmpleadoOut(documento);
    }
}
