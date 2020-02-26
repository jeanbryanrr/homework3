package com.bryanrr.homework3.Service;

import com.bryanrr.homework3.model.Tarea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeseosService {


    private List<Tarea> tareaList;


    public DeseosService() {
        this.tareaList = new ArrayList<>();
        Tarea tarea1 = new Tarea("Ver Mr Robot", new Date(), new Date());
        Tarea tarea2 = new Tarea("Ver Mr Jango", new Date(), new Date());
        this.tareaList.add(tarea1);
        this.tareaList.add(tarea2);

    }


    public void crearTarea(String descripcion, Date fecha, Date hora) {

        Tarea tarea = new Tarea(descripcion, fecha, hora);
        this.tareaList.add(tarea);

    }

    public List<Tarea> listarTareas() {
        return this.tareaList;
    }

    public Tarea getTarea(Integer id) {
        for (Tarea tarea : this.tareaList) {

            if (tarea.getId() == id) {
                return tarea;
            }
        }

        return null;
    }


}
