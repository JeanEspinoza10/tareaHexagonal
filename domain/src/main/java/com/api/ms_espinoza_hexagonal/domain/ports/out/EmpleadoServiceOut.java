package com.api.ms_espinoza_hexagonal.domain.ports.out;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;

public interface EmpleadoServiceOut {
    EmpleadoDTO crearEmpleadoOut(RequestEmpleado empleado);
}
