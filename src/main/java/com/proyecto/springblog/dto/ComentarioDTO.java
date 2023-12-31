package com.proyecto.springblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ComentarioDTO {
    private long id;
    @NotEmpty(message = "no debe estar vacio")
    private String nombre;
    @Email
    @NotEmpty(message = "Debe indicar el correo")
    private String email;
    private String cuerpo;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public ComentarioDTO() {
    }

}
