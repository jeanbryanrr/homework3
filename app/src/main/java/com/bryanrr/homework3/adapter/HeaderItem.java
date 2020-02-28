package com.bryanrr.homework3.adapter;

public class HeaderItem extends  TipoTarea {

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public HeaderItem(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int getTipo() {

            return  TYPE_HEADER;
    }
}
