package com.proyecto.springblog.services;

import com.proyecto.springblog.dto.PublicacionDTO;
import com.proyecto.springblog.dto.PublicacionRespuesta;

public interface PublicacionService {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionRespuesta otenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina,String ordenarPor, String sortDir);
    public PublicacionDTO obtenerPublicacionPorId(Long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);
    public void eliminarPublicacion(Long id);
}
