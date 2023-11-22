package com.proyecto.springblog.services;

import com.proyecto.springblog.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(Long publicacionId);
    public ComentarioDTO comentarioPorId(Long comentarioId);
    public  ComentarioDTO actualizarComentario(Long comentarioId, ComentarioDTO solicitudDeComentario );
    public void eliminarComentario(Long comentarioId);


}
