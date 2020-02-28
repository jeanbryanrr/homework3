package com.bryanrr.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bryanrr.homework3.model.Tarea;
import com.bryanrr.homework3.util.Metodos;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RegistrarTareaActivity extends AppCompatActivity {
    Tarea tarea = null;
    List<Tarea> tareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);


        Button add = findViewById(R.id.btn_add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText txtDescripcion = findViewById(R.id.txt_descripcion);

                TextInputEditText txtTitulo = findViewById(R.id.txt_titulo);
                Date fecha = new Date();

                add(txtTitulo.getText().toString(), txtDescripcion.getText().toString(), fecha);
            }
        });


    }

    @Override
    public void onBackPressed() {

        regresar();

    }

    private void regresar() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("lista", (Serializable) tareas);
        setResult(RESULT_OK, intent);
        finish();
    }


    private void add(String titulo, String desc, Date fecha) {
        Metodos metodos = new Metodos();
        if (titulo.equals("") || desc.equals("")) {
            metodos.mensaje("Ingrese todos los campos", this);
        } else {
            tarea = new Tarea(new Random().nextInt(), titulo, desc, fecha, false);
            tareas.add(tarea);

            metodos.mensaje("Tarea registrada", this);
        }


    }

}

