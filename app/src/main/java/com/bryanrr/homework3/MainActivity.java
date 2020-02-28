package com.bryanrr.homework3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import com.bryanrr.homework3.adapter.HeaderItem;
import com.bryanrr.homework3.adapter.TareaAdapter;
import com.bryanrr.homework3.adapter.TareaItem;
import com.bryanrr.homework3.adapter.TipoTarea;
import com.bryanrr.homework3.model.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Tarea tarea = new Tarea();
    List<TipoTarea> taskList = new ArrayList<>();
    Toolbar toolbar;
    private List<Tarea> tareas = tarea.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        FloatingActionButton button = findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irVistaRegistrar();
            }
        });

        recyclerView = findViewById(R.id.rv_main);
        getListOrdenado();
        adapter = new TareaAdapter(this, taskList, task -> verDetalle(task), (tarea, estado) -> actualizarCheck(tarea,estado));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    TareaAdapter adapter = null;

    private void irVistaRegistrar() {
        Intent intent;
        intent = new Intent(this, RegistrarTareaActivity.class);
        this.startActivityForResult(intent, REGISTRAR);
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void actualizarCheck(Tarea tarea,Boolean estado) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(tarea.getId())) {
                tareas.get(i).setCompletado(estado);
                break;
            }

        }
    }

    private void getListOrdenado() {


        Map<String, List<Tarea>> taskMap = toMap(tareas);
        for (String title : taskMap.keySet()) {
            HeaderItem header = new HeaderItem(title);
            taskList.add(header);
            for (Tarea task : taskMap.get(title)) {
                TareaItem item = new TareaItem(task);
                taskList.add(item);
            }
        }

    }

    private Map<String, List<Tarea>> toMap(List<Tarea> tasks) {
        Map<String, List<Tarea>> map = new TreeMap<>();
        for (Tarea task : tasks) {
            List<Tarea> value = map.get(task.getHumanDate());
            if (value == null) {
                value = new ArrayList<>();
                map.put(task.getHumanDate(), value);
            }
            value.add(task);
        }
        return map;
    }

    public void verDetalle(Tarea tarea) {

        Intent intent = new Intent(this, DetalleTareaActivity.class);
        intent.putExtra("tarea", tarea);
        startActivityForResult(intent, MOSTRAR);

    }

    static final int REGISTRAR = 1;
    static final int MOSTRAR = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case REGISTRAR: {

                if (resultCode == RESULT_OK) {


                    List<Tarea> dataNueva = (List<Tarea>) data.getSerializableExtra("lista");

                    if (dataNueva.size() > 0) {
                        taskList.clear();
                        tareas.addAll(dataNueva);
                        getListOrdenado();
                        adapter.notifyDataSetChanged();
                    }

                }

            }

            break;
            case MOSTRAR: {

                if (resultCode == RESULT_OK) {


                    Tarea tarea = (Tarea) data.getSerializableExtra("tarea");


                    if (tarea != null) {
                        taskList.clear();
                        editar(tarea);
                        getListOrdenado();
                        adapter.notifyDataSetChanged();

                    }


                }

                break;
            }


        }


    }

    public void editar(Tarea tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(tarea.getId())) {
                tareas.remove(i);
                tareas.add(tarea);
                break;
            }

        }
    }

}
