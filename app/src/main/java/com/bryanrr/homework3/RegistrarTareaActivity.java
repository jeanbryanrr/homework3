package com.bryanrr.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bryanrr.homework3.Service.DeseosService;
import com.bryanrr.homework3.model.Tarea;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);


        Button add = findViewById(R.id.btn_add);
        Button back = findViewById(R.id.btn_back);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText txtDescripcion = findViewById(R.id.txt_descripcion);
                TextInputEditText txtFecha = findViewById(R.id.txt_fecha);
                TextInputEditText txtHora = findViewById(R.id.txt_hora);
                Date fecha = null;
                Date hora = null;
                // "yyyy-MM-dd'T'HH:mm:ss'Z'"
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                try {
                    fecha = format1.parse(txtFecha.getText().toString());
                    hora = format2.parse(txtHora.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                add(txtDescripcion.getText().toString(), fecha, hora);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                finish();
            }
        });


    }


    private void add(String desc, Date fecha, Date hora) {
        DeseosService service = new DeseosService();
        service.crearTarea(desc, fecha, hora);
    }

/*    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
try {
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        System.out.println("Current Date Time : " + dateTime);
    } catch (ParseException e) {
        e.printStackTrace();
    }*/
}

