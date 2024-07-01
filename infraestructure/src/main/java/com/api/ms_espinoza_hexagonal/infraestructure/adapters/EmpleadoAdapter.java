package com.api.ms_espinoza_hexagonal.infraestructure.adapters;

import com.api.ms_espinoza_hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.api.ms_espinoza_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseBase;
import com.api.ms_espinoza_hexagonal.domain.aggregates.response.ResponseReniec;
import com.api.ms_espinoza_hexagonal.domain.ports.out.EmpleadoServiceOut;
import com.api.ms_espinoza_hexagonal.infraestructure.dao.EmpleadoRepository;
import com.api.ms_espinoza_hexagonal.infraestructure.entity.EmpleadoEntity;
import com.api.ms_espinoza_hexagonal.infraestructure.mapper.EmpleadoMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final RestTemplate restTemplate;


    @Value("${token}")
    private String tokenApi;

    @Override
    public EmpleadoDTO crearEmpleadoOut(RequestEmpleado empleado) {
        EmpleadoEntity empleadoEntity = getEntityRestTemplate(empleado);

        if(empleadoEntity !=null){
            return empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
        }
        else{
            ResponseBase responseBase = new ResponseBase();
            responseBase.setCode(440);
            responseBase.setMessage("Errror");
            return empleadoMapper.mapToDtoResponseBase(responseBase);
        }
    }


    private EmpleadoEntity getEntityRestTemplate(RequestEmpleado requestEmpleado){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero="+requestEmpleado.getNumeroDocumento();

        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(tokenApi)),
                    ResponseReniec.class
            );
            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();

            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellido(responseReniec.getApellidoPaterno());
            empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            empleadoEntity.setCargo("General");

            return empleadoEntity;
        }catch (HttpClientErrorException e){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " +e.getStatusCode());
        }

        return null;
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + token);
        return headers;
    }
}
