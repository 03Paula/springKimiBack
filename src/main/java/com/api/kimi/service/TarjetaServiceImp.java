package com.api.kimi.service;

import com.api.kimi.dto.converter.TarjetaDTOConverter;
import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.model.Usuario;
import com.api.kimi.model.Tarjeta;
import com.api.kimi.repository.UsuarioRepository;
import com.api.kimi.repository.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarjetaServiceImp implements TarjetaService{

    @Autowired
    private TarjetaRepository tarjetaRepository;
    private final TarjetaDTOConverter tarjetaDTOConverter;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<TarjetaDTO> findAll(){
        List<TarjetaDTO> dtoList = tarjetaRepository.findAll().stream().map(tarjetaDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TarjetaDTO> findById(Long id) {
        return  tarjetaRepository.findById(id).map(tarjetaDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearTarjetaDTO crearTarjetaDTO) {
        Optional<Usuario> cliente;
        cliente = usuarioRepository.findById(crearTarjetaDTO.getUsuarioId());
        if(cliente.isPresent()){
            Tarjeta nTarjeta = new Tarjeta();
            nTarjeta.setUsuario(cliente.get());
            nTarjeta.setN_tarjeta(crearTarjetaDTO.getN_tarjeta());
            nTarjeta.setCvv(crearTarjetaDTO.getCvv());
            nTarjeta.setTitular(crearTarjetaDTO.getTitular());
            nTarjeta.setVencimiento(crearTarjetaDTO.getVencimiento());
            return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaRepository.save(nTarjeta));
        } else{
            throw new UsuarioNotFoundException(crearTarjetaDTO.getUsuarioId());
        }
    }

    @Override
    public Tarjeta update(CrearTarjetaDTO modTarjeta ,Long id) {
        Optional<Usuario> cliente;
        cliente = usuarioRepository.findById(modTarjeta.getUsuarioId());
        if (cliente.isPresent()){
            return tarjetaRepository.findById(id).map(t -> {
                t.setUsuario(cliente.get());
                t.setVencimiento(modTarjeta.getVencimiento());
                t.setCvv(modTarjeta.getCvv());
                t.setTitular(modTarjeta.getTitular());
                t.setN_tarjeta(modTarjeta.getN_tarjeta());
                return tarjetaRepository.save(t);
            }).orElseThrow(() -> new DireccionNotFoundException(id));
        } else{
            throw new UsuarioNotFoundException(modTarjeta.getUsuarioId());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tarjetaRepository.deleteById(id);
    }


    @Override
    public List<TarjetaDTO> findByUsuarioId(Long usuario_id) {

        return tarjetaRepository.findByUsuarioId(usuario_id).stream().map(tarjetaDTOConverter::convertToDTO).collect(Collectors.toList());

    }
}
