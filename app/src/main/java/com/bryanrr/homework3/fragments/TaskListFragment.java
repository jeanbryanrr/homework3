package com.bryanrr.homework3.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryanrr.homework3.DetalleTareaActivity;
import com.bryanrr.homework3.R;
import com.bryanrr.homework3.adapter.HeaderItem;
import com.bryanrr.homework3.adapter.TareaAdapter;
import com.bryanrr.homework3.adapter.TareaItem;
import com.bryanrr.homework3.adapter.TipoTarea;
import com.bryanrr.homework3.model.Tarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

    List<TipoTarea> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    Tarea tarea = new Tarea();
    private List<Tarea> tareas = tarea.getData();
    TareaAdapter adapter;

    public TaskListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_main);
        getListOrdenado();
        adapter = new TareaAdapter(getContext(), taskList, task -> verDetalle(task), (tarea, estado) -> actualizarCheck(tarea, estado));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    static final int MOSTRAR = 2;

    public void verDetalle(Tarea tarea) {

        Intent intent = new Intent(getContext(), DetalleTareaActivity.class);
        intent.putExtra("tarea", tarea);
        startActivityForResult(intent, MOSTRAR);

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

    public void editar(Tarea tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(tarea.getId())) {
                tareas.remove(i);
                tareas.add(tarea);
                break;
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
}
