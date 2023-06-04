package com.api.kimi.service;

import com.api.kimi.dto.converter.DireccionDTOConverter;
import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.error.DireccionUsuarioNotFoundException;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.model.Usuario;
import com.api.kimi.model.Direccion;
import com.api.kimi.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<DireccionDTO> findAll(){
        List<DireccionDTO> dtoList = direccionRepository.findAll().stream().map(direccionDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DireccionDTO> findById(Long id) {
        return  direccionRepository.findById(id).map(direccionDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearDireccionDTO crearDireccion) {
        Optional<Usuario> cliente;
        cliente = usuarioRepository.findById(crearDireccion.getUsuarioId());
        Direccion nDireccion = new Direccion();
        if(cliente.isPresent()) {
            nDireccion.setUsuario(cliente.get());
            nDireccion.setCiudad(crearDireccion.getCiudad());
            nDireccion.setCodigo_postal(crearDireccion.getCodigo_postal());
            nDireccion.setNombre_calle(crearDireccion.getNombre_calle());
            nDireccion.setPais(crearDireccion.getPais());

            if(!crearDireccion.getPiso().isEmpty()) {
                nDireccion.setPiso(crearDireccion.getPiso());
            }else{
                nDireccion.setPiso(null);
            }

            nDireccion.setProvincia(crearDireccion.getProvincia());
            return ResponseEntity.status(HttpStatus.CREATED).body(direccionRepository.save(nDireccion));
        }else{
            throw new UsuarioNotFoundException(crearDireccion.getUsuarioId());
        }

    }

    @Override
    public Direccion update(CrearDireccionDTO modDireccion ,Long id) {
        Optional<Usuario> cliente;
        cliente = usuarioRepository.findById(modDireccion.getUsuarioId());
        if(cliente.isPresent()){
            return direccionRepository.findById(id).map(dir -> {
                dir.setUsuario(cliente.get());
                dir.setCiudad(modDireccion.getCiudad());
                dir.setCodigo_postal(modDireccion.getCodigo_postal());
                dir.setNombre_calle(modDireccion.getNombre_calle());
                dir.setPais(modDireccion.getPais());
                dir.setPiso(modDireccion.getPiso());
                dir.setProvincia(modDireccion.getProvincia());
                return direccionRepository.save(dir);
            }).orElseThrow(() -> new DireccionNotFoundException(id));
        }else{
            throw new UsuarioNotFoundException(modDireccion.getUsuarioId());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        direccionRepository.deleteById(id);
    }

    @Override
    public List<DireccionDTO> findByUsuarioId(Long id_usuario) {
        return direccionRepository.findByUsuarioId(id_usuario).stream().map(direccionDTOConverter::convertToDTO).collect(Collectors.toList());

    }


}
