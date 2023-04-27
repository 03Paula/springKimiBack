package com.api.kimi.service;

import com.api.kimi.dto.converter.DireccionDTOConverter;
import com.api.kimi.dto.converter.TarjetaDTOConverter;
import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.dto.tarjeta.CrearTarjetaDTO;
import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.error.DireccionNotFoundException;
import com.api.kimi.model.Direccion;
import com.api.kimi.model.Tarjeta;
import com.api.kimi.repository.DireccionRepository;
import com.api.kimi.repository.TarjetaRepository;
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
public class TarjetaServiceImp implements TarjetaService{

    @Autowired
    private TarjetaRepository tarjetaRepository;
    private final TarjetaDTOConverter tarjetaDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public Iterable<TarjetaDTO> findAll(){
        List<TarjetaDTO> dtoList = tarjetaRepository.findAll().stream().map(tarjetaDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarjeta> findById(Long id) {
        return  tarjetaRepository.findById(id);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearTarjetaDTO crearTarjetaDTO) {
        Tarjeta nTarjeta = new Tarjeta();
        nTarjeta.setN_tarjeta(crearTarjetaDTO.getN_tarjeta());
        nTarjeta.setCvv(crearTarjetaDTO.getCvv());
        nTarjeta.setTitular(crearTarjetaDTO.getTitular());
        nTarjeta.setVencimiento(crearTarjetaDTO.getVencimiento());
        return ResponseEntity.status(HttpStatus.CREATED).body(tarjetaRepository.save(nTarjeta));
    }

    @Override
    public Tarjeta update(CrearTarjetaDTO modTarjeta ,Long id) {
        return tarjetaRepository.findById(id).map(t -> {
            t.setVencimiento(modTarjeta.getVencimiento());
            t.setCvv(modTarjeta.getCvv());
            t.setTitular(modTarjeta.getTitular());
            t.setN_tarjeta(modTarjeta.getN_tarjeta());
            return tarjetaRepository.save(t);
        }).orElseThrow(() -> new DireccionNotFoundException(id));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tarjetaRepository.deleteById(id);
    }
}
