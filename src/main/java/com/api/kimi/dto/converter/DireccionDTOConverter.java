package com.api.kimi.dto.converter;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Cliente;
import com.api.kimi.model.Direccion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DireccionDTOConverter {
    private final ModelMapper modelMapper;

    public DireccionDTO convertToDTO(Direccion direccion){

        return modelMapper.map(direccion, DireccionDTO.class);
    }
}
