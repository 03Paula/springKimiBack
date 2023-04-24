package com.api.kimi.dto.converter;

import com.api.kimi.dto.cliente.ClienteDTO;
import com.api.kimi.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteDTOConverter {

    private final ModelMapper modelMapper;

    public ClienteDTO convertToDTO(Cliente cliente){

        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
