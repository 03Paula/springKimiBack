package com.api.kimi.dto.converter;

import com.api.kimi.dto.usuario.UsuarioDTO;
import com.api.kimi.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {

    private final ModelMapper modelMapper;

    public UsuarioDTO convertToDTO(Usuario usuario){

        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
