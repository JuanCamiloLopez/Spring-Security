package com.proyecto.springblog.services;
import com.proyecto.springblog.dto.PublicacionDTO;
import com.proyecto.springblog.dto.PublicacionRespuesta;
import com.proyecto.springblog.entities.Publicaciones;
import com.proyecto.springblog.exceptions.ResourseNotFoundException;
import com.proyecto.springblog.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

   @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
    Publicaciones publicacion = mapearEntidad(publicacionDTO);
    Publicaciones publicacionNueva = publicacionRepository.save(publicacion);
    PublicacionDTO publicacionRespuesta = mapearDTO(publicacionNueva);
    return publicacionRespuesta;
    }
    @Override
    public PublicacionRespuesta otenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina,String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina, sort);
        Page<Publicaciones> publicacionesPage = publicacionRepository.findAll(pageable);
        List<Publicaciones> publicaciones = publicacionesPage.getContent();
        List<PublicacionDTO> contenido = publicaciones
                .stream()
                .map(publicacion -> mapearDTO(publicacion))
                .collect(Collectors.toList());
        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroPagina(publicacionesPage.getNumber());
        publicacionRespuesta.setMedidaPagina(publicacionesPage.getSize());
        publicacionRespuesta.setTotalElementos(publicacionesPage.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicacionesPage.getTotalPages());
        publicacionRespuesta.setUltima(publicacionesPage.isLast());
        return publicacionRespuesta;
    }
    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicaciones publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Publicacion","id",id));
        return mapearDTO(publicacion);
    }
    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicaciones publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Publicacion","id",id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicaciones publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }
    @Override
    public void eliminarPublicacion(Long id) {
        Publicaciones publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Publicacion","id",id));
        publicacionRepository.delete(publicacion);
    }
    private PublicacionDTO mapearDTO(Publicaciones publicacion){
      PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
        return publicacionDTO;
    }
    private Publicaciones mapearEntidad(PublicacionDTO publicacionDTO){
        Publicaciones publicacion = modelMapper.map(publicacionDTO, Publicaciones.class);
        return publicacion;
    }
}
