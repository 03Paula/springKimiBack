package com.api.kimi.service;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.dto.usuario.CrearUsuarioDTO;
import com.api.kimi.dto.converter.ClienteDTOConverter;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.error.EmailDuplicatedException;
import com.api.kimi.model.Usuario;
import com.api.kimi.model.UsuarioRol;
import com.api.kimi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final ClienteDTOConverter clienteDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public Iterable<UsuarioDTO> findAll(){
        List<UsuarioDTO> dtoList = usuarioRepository.findAll().stream().map(clienteDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return  usuarioRepository.findById(id).map(clienteDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearUsuarioDTO crearCliente) {
        if(usuarioRepository.findByEmail(crearCliente.getEmail()).isEmpty()){
            Usuario nUsuario = new Usuario();
            nUsuario.setNombre(crearCliente.getNombre());
            nUsuario.setApellidos(crearCliente.getApellidos());
            nUsuario.setEmail(crearCliente.getEmail());
            nUsuario.setTelefono(crearCliente.getTelefono());
            nUsuario.setContrasenia(crearCliente.getContrasenia());
            nUsuario.setRol(UsuarioRol.USUARIO.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(nUsuario));
        }else{
            throw new EmailDuplicatedException(crearCliente.getEmail());
        }
    }

    @Override
    public Usuario update(CrearUsuarioDTO modCliente , Long id) {
        Usuario usuarioEmail = usuarioRepository.findByEmail(modCliente.getEmail()).orElseThrow(() -> new EmailDuplicatedException(modCliente.getEmail()));
        return usuarioRepository.findById(id).map(cliente -> {
            cliente.setNombre(modCliente.getNombre());
            cliente.setApellidos(modCliente.getApellidos());
            cliente.setEmail(usuarioEmail.getEmail());
            cliente.setTelefono(modCliente.getTelefono());
            cliente.setContrasenia(modCliente.getContrasenia());
            return usuarioRepository.save(cliente);
        }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findClientByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(clienteDTOConverter::convertToDTO);
    }
}
