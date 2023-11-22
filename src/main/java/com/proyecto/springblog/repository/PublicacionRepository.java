package com.proyecto.springblog.repository;

import com.proyecto.springblog.entities.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicaciones,Long> {

}
