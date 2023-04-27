package com.api.kimi.service;

import com.api.kimi.dto.converter.DireccionDTOConverter;
import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.model.Direccion;
import com.api.kimi.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionServiceImp implements DireccionService{

    @Autowired
    private DireccionRepository direccionRepository;
    private final DireccionDTOConverter direccionDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public Iterable<DireccionDTO> findAll(){
        List<DireccionDTO> dtoList = direccionRepository.findAll().stream().map(direccionDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> findById(Long id) {
        return  direccionRepository.findById(id);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearDireccionDTO crearDireccion) {
        Direccion nDireccion = new Direccion();
        nDireccion.setCiudad(crearDireccion.getCiudad());
        nDireccion.setCodigo_postal(crearDireccion.getCodigo_postal());
        nDireccion.setNombre_calle(crearDireccion.getNombre_calle());
        nDireccion.setPais(crearDireccion.getPais());
        nDireccion.setPiso(crearDireccion.getPiso());
        nDireccion.setProvincia(crearDireccion.getProvincia());
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionRepository.save(nDireccion));
    }

    @Override
    public Direccion update(CrearDireccionDTO modDireccion ,Long id) {
        return direccionRepository.findById(id).map(dir -> {
            dir.setCiudad(modDireccion.getCiudad());
            dir.setCodigo_postal(modDireccion.getCodigo_postal());
            dir.setNombre_calle(modDireccion.getNombre_calle());
            dir.setPais(modDireccion.getPais());
            dir.setPiso(modDireccion.getPiso());
            dir.setProvincia(modDireccion.getProvincia());
            return direccionRepository.save(dir);
        }).orElseThrow(() -> new DireccionNotFoundException(id));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        direccionRepository.deleteById(id);
    }
}
