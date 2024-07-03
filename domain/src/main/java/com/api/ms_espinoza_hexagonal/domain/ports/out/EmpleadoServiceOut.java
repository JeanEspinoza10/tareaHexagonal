package com.api.ms_espinoza_hexagonal.domain.ports.out;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceOut {
    ResponseBase crearEmpleadoOut(RequestEmpleado empleado);
    ResponseBase buscarEmpleadoOut(String documento);
}
