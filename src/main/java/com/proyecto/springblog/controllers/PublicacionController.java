package com.proyecto.springblog.controllers;

import com.proyecto.springblog.dto.PublicacionDTO;
import com.proyecto.springblog.dto.PublicacionRespuesta;
import com.proyecto.springblog.services.PublicacionService;
import com.proyecto.springblog.utils.AppConstantes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
    @Autowired
    PublicacionService publicacionService;

    @GetMapping
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return publicacionService.otenerTodasLasPublicaciones(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(publicacionService.actualizarPublicacion(publicacionDTO, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada con exito", HttpStatus.OK);
    }
}
