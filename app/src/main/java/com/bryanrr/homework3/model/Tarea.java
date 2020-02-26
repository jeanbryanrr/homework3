package com.bryanrr.homework3.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Tarea {

    private Integer id;
    private String descripcion;
    private Date creadoEn;
    private Boolean completado;
    private Date hora;

    public Tarea(String descripcion, Date creadoEn, Date hora) {

        this.id = new Random().nextInt();
        this.descripcion = descripcion;
        this.completado = false;
        this.creadoEn = creadoEn;
        this.hora = hora;


    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
}
