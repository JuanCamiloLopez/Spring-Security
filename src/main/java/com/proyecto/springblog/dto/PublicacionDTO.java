package com.proyecto.springblog.dto;

import com.proyecto.springblog.entities.Comentario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PublicacionDTO  {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "El titulo de la publicación deberia tener de 2 a mas caracteres")
    private String titulo;
    @NotEmpty
    @Size(min = 11, message = "El titulo de la publicación deberia tener de 11 a mas caracteres")
    private String descripcion;
    private String contenido;
    private Set<Comentario> comentarios;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
