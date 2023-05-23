package com.api.kimi.controller;

import com.api.kimi.dto.direccion.CrearDireccionDTO;
import com.api.kimi.dto.direccion.DireccionDTO;
import com.api.kimi.model.Direccion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DireccionController {
    public ResponseEntity<?> create(@RequestBody CrearDireccionDTO direccion);

    public ResponseEntity<?> read(@PathVariable(value = "id") Long direccionId);

    public Direccion updateDireccion(@RequestBody CrearDireccionDTO direccionDetails, @PathVariable Long id);

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long direccionId);

    public List<DireccionDTO> readAll();

    public ResponseEntity<?> getDirectionByUser(@PathVariable Long id);

}
