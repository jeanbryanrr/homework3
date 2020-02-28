package com.bryanrr.homework3.model;

import android.content.Intent;

import com.bryanrr.homework3.util.DateUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Tarea implements Serializable {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Date creadoEn;
    private Boolean completado;


    public Tarea(Integer id, String titulo, String descripcion, Date creadoEn, Boolean completado) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completado = completado;
        this.creadoEn = creadoEn;


    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        return format.format(creadoEn);
    }

    public String getHumanDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(creadoEn);
    }


    public List<Tarea> getData() {
        Tarea tarea1 = new Tarea(new Random().nextInt(), "Ver Mr Robot", "des", new DateUtil().currentDate("29/02/2020 02:00"), true);
        Tarea tarea2 = new Tarea(new Random().nextInt(), "Ver Mr Jango", "des", new DateUtil().currentDate("29/02/2020 02:00"), false);

        List<Tarea> tareas = new ArrayList<>();
        tareas.add(tarea1);
        tareas.add(tarea2);
        return tareas;
    }

    public Tarea() {
    }
}
