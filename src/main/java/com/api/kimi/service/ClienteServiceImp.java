package com.api.kimi.service;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.cliente.CrearClienteDTO;
import com.api.kimi.dto.converter.ClienteDTOConverter;
import com.api.kimi.error.ClienteNotFoundException;
import com.api.kimi.model.Cliente;
import com.api.kimi.repository.ClienteRepository;
import com.api.kimi.response.response;
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
public class ClienteServiceImp implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;
    private final ClienteDTOConverter clienteDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ClienteDTO> findAll(){
        List<ClienteDTO> dtoList = clienteRepository.findAll().stream().map(clienteDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return  clienteRepository.findById(id);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearClienteDTO crearCliente) {
        Cliente nCliente = new Cliente();
        nCliente.setNombre(crearCliente.getNombre());
        nCliente.setApellidos(crearCliente.getEmail());
        nCliente.setEmail(crearCliente.getEmail());
        nCliente.setTelefono(crearCliente.getTelefono());
        nCliente.setContrasenia(crearCliente.getContrasenia());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(nCliente));
    }

    @Override
    public Cliente update(CrearClienteDTO modCliente ,Long id) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(modCliente.getNombre());
            cliente.setApellidos(modCliente.getApellidos());
            cliente.setEmail(modCliente.getEmail());
            cliente.setTelefono(modCliente.getTelefono());
            cliente.setContrasenia(modCliente.getContrasenia());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ClienteNotFoundException(id));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
