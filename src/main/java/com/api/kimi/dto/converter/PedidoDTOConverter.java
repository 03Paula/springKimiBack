package com.api.kimi.dto.converter;

import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoDTOConverter {
    private final ModelMapper modelMapper;

    public PedidoDTO convertToDTO(Pedido pedido){

        return modelMapper.map(pedido, PedidoDTO.class);
    }
}
