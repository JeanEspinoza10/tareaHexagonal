package com.api.ms_espinoza_hexagonal.infraestructure.dao;

import com.api.ms_espinoza_hexagonal.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {
    boolean existsByNumDoc(String numDoc);
    Optional<EmpleadoEntity> findByNumDoc(String numDoc);
}
