package com.api.ms_espinoza_hexagonal.infraestructure.adapters;

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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final RestTemplate restTemplate;


    @Value("${token}")
    private String tokenApi;

    @Override
    public ResponseBase crearEmpleadoOut(RequestEmpleado empleado) {
        String documento = empleado.getNumeroDocumento();
        if(documento != null && !documento.isEmpty()){
            boolean exist = empleadoRepository.existsByNumDoc(empleado.getNumeroDocumento());
            if(exist){
                return new ResponseBase(200,"Existe un empleado con el documento enviado",Optional.empty());
            }else{
                EmpleadoEntity empleadoEntity = getEntityRestTemplate(empleado);
                if(empleadoEntity !=null){
                    empleadoEntity.setEdad(empleado.getEdad());
                    empleadoEntity.setDepartamento(empleado.getDepartamento());
                    empleadoEntity.setSalario(empleado.getSalario());
                    empleadoEntity.setTelefono(empleado.getTelefono());
                    empleadoEntity.setCorreo(empleado.getCorreo());
                    empleadoEntity.setEdad(empleado.getEdad());
                    empleadoEntity.setEstado(true);
                    empleadoEntity.setDireccion(empleado.getDireccion());
                    empleadoRepository.save(empleadoEntity);
                    return  new ResponseBase(200,"Se realizo el grabado correctamente",Optional.empty());
                }else{
                    return  new ResponseBase(400,"Error en la búsqueda en RENIEC",Optional.empty());
                }
            }
        }
        return new ResponseBase(400, "Error no se encontro el documento",Optional.empty() );
    }

    @Override
    public ResponseBase buscarEmpleadoOut(String documento) {
        String documentoQuery = documento;
        if(documentoQuery != null && !documentoQuery.isEmpty()){
            Optional<EmpleadoEntity> busqueda  =  empleadoRepository.findByNumDoc(documento);
            if(busqueda.isPresent()){
                return new ResponseBase(200, "Se encontro datos",Optional.of(busqueda.get()));
            }else {
                return new ResponseBase(200,"No hay datos", busqueda.empty());
            }
        }
        return new ResponseBase(400,"Error en envio de params",Optional.empty());
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
