package com.bryanrr.homework3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import com.bryanrr.homework3.adapter.HeaderItem;
import com.bryanrr.homework3.adapter.TareaAdapter;
import com.bryanrr.homework3.adapter.TareaItem;
import com.bryanrr.homework3.adapter.TipoTarea;
import com.bryanrr.homework3.adapter.ViewPagerAdapter;
import com.bryanrr.homework3.fragments.TaskListFragment;
import com.bryanrr.homework3.model.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;
    List<TipoTarea> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    Tarea tarea = new Tarea();
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
        viewPager = findViewById(R.id.vp_main);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tbl_main);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TaskListFragment(),"Tareas");
        adapter.addFragment(new TaskListFragment(),"Actividades");
        viewPager.setAdapter(adapter);
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
