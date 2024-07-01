package com.api.ms_espinoza_hexagonal.infraestructure.dao;

import com.api.ms_espinoza_hexagonal.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {
}
