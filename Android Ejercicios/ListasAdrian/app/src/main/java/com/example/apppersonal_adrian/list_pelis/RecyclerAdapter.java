package com.example.apppersonal_adrian.list_pelis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppersonal_adrian.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<PeList> elementosPelis;
    private RecyclerItemClick itemClick;

    public RecyclerAdapter(List<PeList> elementosPelis, RecyclerItemClick itemClick) {
        this.elementosPelis = elementosPelis;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peli_list, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final PeList item = elementosPelis.get(position);
        holder.imgItem.setImageResource(item.getCaratula());
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvDescripcion.setText(item.getAnio());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementosPelis.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvTitulo;
        private TextView tvDescripcion;

        public RecyclerHolder(@NonNull View mitemView) {
            super(mitemView);

            imgItem = itemView.findViewById(R.id.pl_caratula);
            tvTitulo = itemView.findViewById(R.id.pl_titulo);
            tvDescripcion = itemView.findViewById(R.id.pl_anio);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(PeList item);
    }
}
