package com.bryanrr.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bryanrr.homework3.model.Tarea;
import com.bryanrr.homework3.util.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.Date;

public class DetalleTareaActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    TextInputEditText txtTitulo = null;
    TextInputEditText txtDesc = null;
    TextInputEditText txtFecha = null;
    TextInputEditText txtHora = null;
    FloatingActionButton btnHabilitar = null;
    TextInputEditText txtCompletado = null;
    Button btnRegistrar = null;
    Tarea tarea = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);

        toolbar = findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");


        Intent intent = getIntent();
        tarea = (Tarea) intent.getSerializableExtra("tarea");

        txtTitulo = findViewById(R.id.txt_titulo);
        txtDesc = findViewById(R.id.txt_descripcion);
        txtFecha = findViewById(R.id.txt_fecha);
        txtHora = findViewById(R.id.txt_hora);
        txtCompletado = findViewById(R.id.txtCompletado);

        btnHabilitar = findViewById(R.id.fb_hab);
        btnHabilitar.setOnClickListener(this::onClick);
        btnRegistrar = findViewById(R.id.btn_registrar);
        btnRegistrar.setOnClickListener(this::onClick);
        btnRegistrar.setEnabled(false);
        txtTitulo.setText(tarea.getTitulo());
        txtDesc.setText(tarea.getDescripcion());
        txtFecha.setText(tarea.getHumanDate());
        txtHora.setText(tarea.getTime());

        if (tarea.getCompletado()) {
            txtCompletado.setText("Tarea Terminada");
        } else {
            txtCompletado.setText("Tarea sin Terminar");
        }


    }

    Boolean flag = true;

    private void habilitarEdicion() {
        if (flag) {
            txtTitulo.setEnabled(true);
            txtDesc.setEnabled(true);
            btnRegistrar.setEnabled(true);
        } else {
            txtTitulo.setEnabled(false);
            txtDesc.setEnabled(false);
            btnRegistrar.setEnabled(false);

        }


        flag = !flag;
    }

    Boolean registrado = false;

    @Override
    public void onClick(View v) {
        Metodos metodos = new Metodos();

        if (btnHabilitar.getId() == v.getId()) {
            habilitarEdicion();
        } else if (btnRegistrar.getId() == v.getId()) {

            String titulo = txtTitulo.getText().toString();
            String desc = txtDesc.getText().toString();

            if (titulo.equals("") || desc.equals("")) {
                metodos.mensaje("Ingrese todos los campos", this);
            } else {
                tarea.setTitulo(titulo);
                tarea.setDescripcion(desc);
                tarea.setCreadoEn(new Date());
                registrado = true;
                metodos.mensaje("Tarea actualizada", this);
            }

        }
    }

    @Override
    public void onBackPressed() {
        regresar();
    }

    private void regresar() {

        Intent intent = new Intent(this, MainActivity.class);
        if (registrado) {
            intent.putExtra("tarea", tarea);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
