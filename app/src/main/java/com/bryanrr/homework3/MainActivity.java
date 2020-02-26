package com.bryanrr.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bryanrr.homework3.Service.DeseosService;
import com.bryanrr.homework3.model.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DeseosService deseosService = new DeseosService();
    private RecyclerView recyclerView;
    private TareaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rcv_main);
        adapter = new TareaAdapter(deseosService.listarTareas());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


        FloatingActionButton button = findViewById(R.id.btn_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irVistaRegistrar();
            }
        });


    }

    private void irVistaRegistrar() {
        Intent intent;
        intent = new Intent(this, RegistrarTareaActivity.class);
        this.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();


    }
}
