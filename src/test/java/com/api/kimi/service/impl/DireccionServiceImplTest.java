package com.api.kimi.service.impl;

import com.api.kimi.dto.converter.DireccionDTOConverter;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Direccion;
import com.api.kimi.model.Usuario;
import com.api.kimi.repository.DireccionRepository;
import com.api.kimi.service.DireccionService;
import com.api.kimi.service.DireccionServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DireccionServiceImplTest {

    @InjectMocks
    DireccionServiceImp direccionService;

    @Mock
    DireccionRepository direccionRepository;

    List<Direccion> direcciones = new ArrayList<Direccion>();
    List<DireccionDTO> direccionesDTO = new ArrayList<DireccionDTO>();
    DireccionDTOConverter direccionDTOConverter;

    @BeforeEach
    void setup() throws Exception {
        Direccion mock_dir = new Direccion();
        mock_dir.setProvincia("Provincia test");
        mock_dir.setPais("Pais test");
        mock_dir.setCiudad("Ciudad test");
        mock_dir.setNombre_calle("Calle test Nº21");
        mock_dir.setCodigo_postal(11510);
        mock_dir.setId(Long.valueOf(1));
        mock_dir.setPiso("Piso test");

        Usuario mock_usuario = new Usuario();
        mock_usuario.setId(Long.valueOf(1));
        mock_usuario.setNombre("Nombre Test");
        mock_usuario.setRol("USUARIO");
        mock_usuario.setApellidos("Apellidos test");
        mock_usuario.setContrasenia("Contraseña test");
        mock_usuario.setEmail("Email test");
        mock_usuario.setTelefono("345-567-234");

        mock_dir.setUsuario(mock_usuario);

        direcciones.add(mock_dir);
    }


    @Test
    public void findAllAdressesTest() throws Exception {
        when(direccionRepository.findAll()).thenReturn(direcciones);

        List<DireccionDTO> DirBBDD = (List<DireccionDTO>) direccionService.findAll();
        Assertions.assertNotNull(DirBBDD);
        Assertions.assertEquals(direcciones, DirBBDD);
    }

    @Test
    public void findByUsuarioTest() throws Exception {

        when(direccionRepository.findByUsuarioId(1L)).thenReturn(direcciones);

        List<DireccionDTO> DirBBDD = direccionService.findByUsuarioId(1L);
        Assertions.assertNotNull(DirBBDD);

    }

}
