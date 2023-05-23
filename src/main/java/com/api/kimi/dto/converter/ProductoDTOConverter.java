package com.api.kimi.dto.converter;

import com.api.kimi.dto.producto.ProductoDTO;
import com.api.kimi.model.Producto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
    private final ModelMapper modelMapper;
    public ProductoDTO convertToDTO(Producto producto){

        return modelMapper.map(producto, ProductoDTO.class);
    }
}
