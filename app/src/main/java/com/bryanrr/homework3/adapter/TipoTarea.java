package com.bryanrr.homework3.adapter;

public abstract class TipoTarea {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_TASK = 1;
    abstract public int getTipo();
}
