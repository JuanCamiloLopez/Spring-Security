package com.proyecto.springblog.controllers;

import com.proyecto.springblog.dto.ComentarioDTO;
import com.proyecto.springblog.entities.Comentario;
import com.proyecto.springblog.repository.ComentarioRepository;
import com.proyecto.springblog.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicaciones/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> comentarioPorId(@PathVariable(value = "id")Long comentarioId){
        return new ResponseEntity<>(comentarioService.comentarioPorId(comentarioId),HttpStatus.OK);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> comentariosPorIdPublicacion(@PathVariable(value = "publicacionId") Long publicacionId){
        return comentarioService.obtenerComentarioPorPublicacionId(publicacionId);
    }
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @Valid  @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId,comentarioDTO),HttpStatus.CREATED);
    }
    @PutMapping("/publicaciones/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> actualizarComentario( @PathVariable(value = "id")Long comentarioId, @Valid @RequestBody ComentarioDTO comentarioDTO){
        try {
            ComentarioDTO updatedComentarioDTO = comentarioService.actualizarComentario(comentarioId, comentarioDTO);
            return new ResponseEntity<>(updatedComentarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/publicaciones/comentarios/{id}")
    public void deleteComentario(@PathVariable(value = "id")Long id){
    comentarioService.eliminarComentario(id);
    }
}
