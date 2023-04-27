package com.api.kimi.dto.converter;

import com.api.kimi.dto.tarjeta.TarjetaDTO;
import com.api.kimi.model.Tarjeta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TarjetaDTOConverter {
    private final ModelMapper modelMapper;

    public TarjetaDTO convertToDTO(Tarjeta tarjeta){

        return modelMapper.map(tarjeta, TarjetaDTO.class);
    }
}
