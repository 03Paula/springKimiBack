package com.api.kimi.dto.converter;

import com.api.kimi.dto.carrito.CarritoDTO;
import com.api.kimi.model.Carrito;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarritoDTOConverter {
    private final ModelMapper modelMapper;

    public CarritoDTO convertToDTO(Carrito carrito){

        return modelMapper.map(carrito, CarritoDTO.class);
    }
}
