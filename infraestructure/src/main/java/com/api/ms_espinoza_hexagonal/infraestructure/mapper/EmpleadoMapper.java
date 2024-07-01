package com.api.ms_espinoza_hexagonal.infraestructure.mapper;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;
import com.api.ms_espinoza_hexagonal.infraestructure.entity.EmpleadoEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class EmpleadoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDTO mapToDto(EmpleadoEntity empleadoEntity){
        return modelMapper.map(empleadoEntity, EmpleadoDTO.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDTO empleadoDTO){
        return modelMapper.map(empleadoDTO,EmpleadoEntity.class);
    }

    public EmpleadoDTO mapToDtoResponseBase(ResponseBase responseBase){
        return modelMapper.map(responseBase, EmpleadoDTO.class);
    }

}
