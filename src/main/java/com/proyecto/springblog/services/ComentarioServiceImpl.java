package com.proyecto.springblog.services;

import com.proyecto.springblog.dto.ComentarioDTO;
import com.proyecto.springblog.entities.Comentario;
import com.proyecto.springblog.entities.Publicaciones;
import com.proyecto.springblog.exceptions.ResourseNotFoundException;
import com.proyecto.springblog.repository.ComentarioRepository;
import com.proyecto.springblog.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
     private ComentarioRepository comentarioRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapperEntidad(comentarioDTO);
        Publicaciones publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() -> new ResourseNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return mapperDTO(nuevoComentario);
    }
    @Override
    public List<ComentarioDTO> obtenerComentarioPorPublicacionId(Long publicacionId) {
       List<Comentario> comentariosPorId = comentarioRepository.findByPublicacionId(publicacionId);
       return comentariosPorId.stream().map(comentario->mapperDTO(comentario)).collect(Collectors.toList());
    }
    @Override
    public ComentarioDTO comentarioPorId(Long comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow();
        return mapperDTO(comentario);
    }
    @Override
    public ComentarioDTO actualizarComentario(Long comentarioId, ComentarioDTO solicitudDeComentario) {
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow();
        comentario.setNombre(solicitudDeComentario.getNombre());
        comentario.setEmail(solicitudDeComentario.getEmail());
        comentario.setCuerpo(solicitudDeComentario.getCuerpo());
        comentarioRepository.save(comentario);
        return mapperDTO(comentario);
    }

    @Override
    public void eliminarComentario(Long comentarioId) {
        comentarioRepository.deleteById(comentarioId);
    }

    private ComentarioDTO mapperDTO(Comentario comentario){
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }
    private Comentario mapperEntidad(ComentarioDTO comentarioDTO){
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        return comentario;
    }
}
