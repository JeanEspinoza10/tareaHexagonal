package com.api.ms_espinoza_hexagonal.domain.ports.in;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;

public interface EmpleadoServiceIn {
    EmpleadoDTO crearEmpleadoIn(RequestEmpleado empleado);
}
