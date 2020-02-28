package com.bryanrr.homework3.adapter;

import com.bryanrr.homework3.model.Tarea;

public   class TareaItem extends  TipoTarea  {

    private Tarea tarea;

    public TareaItem(Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea getTarea() {
        return tarea;
    }


    @Override
    public int getTipo() {
          return TYPE_TASK;
    }
}
