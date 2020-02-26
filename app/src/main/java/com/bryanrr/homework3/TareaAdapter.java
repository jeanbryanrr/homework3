package com.bryanrr.homework3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bryanrr.homework3.Service.DeseosService;
import com.bryanrr.homework3.model.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.RecyclerViewHolderx> {

    private List<Tarea> tareaList;

    public TareaAdapter(List<Tarea> list) {
        tareaList = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolderx onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_tareas, parent, false);

        return new RecyclerViewHolderx(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderx holder, int position) {
        Tarea tarea = tareaList.get(position);
        holder.tvFecha.setText("10/10/10");
        holder.tvDescripcion.setText(tarea.getDescripcion());
        holder.cbFlag.setChecked(tarea.getCompletado());
    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }

    public static class RecyclerViewHolderx extends RecyclerView.ViewHolder {

        public TextView tvFecha;
        public TextView tvDescripcion;
        public CheckBox cbFlag;

        public RecyclerViewHolderx(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tv_fecha);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion);
            cbFlag = itemView.findViewById(R.id.check);
        }
    }
}
