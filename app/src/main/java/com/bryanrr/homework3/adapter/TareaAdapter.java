package com.bryanrr.homework3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bryanrr.homework3.R;
import com.bryanrr.homework3.model.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TipoTarea> itemList;
    private OnItemClickListener itemClickListener;
    private OncheckListener checkClickListener;
    private Context context;

    public TareaAdapter(Context context, List<TipoTarea> itemList, OnItemClickListener itemClickListener, OncheckListener checkClickListener) {
        this.context = context;
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
        this.checkClickListener = checkClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TipoTarea.TYPE_HEADER: {
                View itemView = inflater.inflate(R.layout.tarea_item_header, parent, false);
                return new HeaderViewHolder(itemView);
            }
            case TipoTarea.TYPE_TASK: {
                View itemView = inflater.inflate(R.layout.item_tarea, parent, false);
                return new ViewHolder(itemView);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemType = getItemViewType(position);

        switch (itemType) {
            case TipoTarea.TYPE_HEADER: {
                HeaderItem task = (HeaderItem) itemList.get(position);
                HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
                viewHolder.tvTitle.setText(task.getTitulo());
                break;
            }
            case TipoTarea.TYPE_TASK: {
                TareaItem task = (TareaItem) itemList.get(position);
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.tvTitle.setText(task.getTarea().getTitulo());
                viewHolder.tvDescription.setText(task.getTarea().getDescripcion());
                viewHolder.tvDate.setText(task.getTarea().getTime());
                viewHolder.cbxEnable.setChecked(task.getTarea().getCompletado());
                viewHolder.cbxEnable.setOnClickListener(v -> checkClickListener.oncheckedClick(task.getTarea(),viewHolder.cbxEnable.isChecked()));
                viewHolder.container.setOnClickListener(v -> itemClickListener.onItemClick(task.getTarea()));
                break;
            }
            default:
                throw new IllegalStateException("UNSUPPORTED ITEM TYPE");
        }


    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getTipo();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        CheckBox cbxEnable;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cbxEnable = itemView.findViewById(R.id.cbx_task);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_time);
            container = itemView.findViewById(R.id.ll_container);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_header);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Tarea tarea);


    }

    public interface OncheckListener {
        void oncheckedClick(Tarea tarea,Boolean estado);


    }

}
