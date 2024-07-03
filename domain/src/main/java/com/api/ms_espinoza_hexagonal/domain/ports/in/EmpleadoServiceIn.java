package com.api.ms_espinoza_hexagonal.domain.ports.in;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceIn {
    ResponseBase crearEmpleadoIn(RequestEmpleado empleado);
    ResponseBase buscarEmpleadoIn(String documento);
}
