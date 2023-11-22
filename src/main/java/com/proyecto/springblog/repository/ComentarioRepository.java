package com.proyecto.springblog.repository;

import com.proyecto.springblog.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByPublicacionId(long publicacionId);
}
