package com.example.apppersonal_adrian.list_tareas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppersonal_adrian.R;

import java.util.ArrayList;

public class TareaRecyclerAdapter extends RecyclerView.Adapter<TareaRecyclerAdapter.ViewHolder> {


    //declarar las variables
    ArrayList<Tarea> lisTarea;
    //crear variable de tipo de la interface creada
    final private TareaItemClickListener mOnClickListener; //paso 2

    //generamos el constructor
    public TareaRecyclerAdapter(ArrayList<Tarea> lisTarea, TareaItemClickListener mOnClickListener) {
        this.lisTarea = lisTarea;
        this.mOnClickListener = mOnClickListener; // se añade al constructor Paso 3
    }

    //variables para la SharePreferences
    int animAdd = 0;
    int animRemove = 0;

    //animaciones
    private  Animation animacionFlash;
    private  Animation animacionDesplazar;
    private  Animation animacionTransicion;
    private  Animation animacionAparece;
    private  Animation animacionDesaparece;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //tenemos que inflar el layout donde se muestran los elementos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_tarea, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //personalizamos la visualizacion
        Tarea elementoItem = lisTarea.get(position);

        holder.itemView.setAnimation(animacionTransicion);


        if(animAdd == position && position!=0){
            holder.itemView.setAnimation(animacionAparece);
        }

        if(animRemove == position && position!=0){
            holder.itemView.setAnimation(animacionDesaparece);
        }
        if(elementoItem.isFavoritos()){
            holder.itemView.setAnimation(animacionFlash);
        }

        holder.textViewTitulo.setText(lisTarea.get(position).getTitulo());
        holder.textViewDescripcion.setText(elementoItem.getDescripcion());

        //mostrar las imagenes
        if (!elementoItem.isFavoritos()){
            holder.imageViewFavoritos.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        //metodo que devuelve el numero de elementos de la lista
        return lisTarea.size();
    }

    //elementos del layout
    //paso 4 implementar el onClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variables
        TextView textViewTitulo, textViewDescripcion, textViewFecha;
        ImageView imageViewFavoritos;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); //paso 4a agregar el constructor ViewHolder

            //instaciar los objetos
            textViewTitulo = itemView.findViewById(R.id.lt_titulo);
            textViewDescripcion = itemView.findViewById(R.id.lt_descripcion);

            imageViewFavoritos = itemView.findViewById(R.id.lt_favoritos);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onTareaItemClick(position);

        }
    }

    public void addTarea (Tarea t){
        lisTarea.add(t);
        animAdd = lisTarea.indexOf(t);
        notifyDataSetChanged();
    }

    public void removeTarea(Tarea t){
        lisTarea.remove(t);
        animRemove = lisTarea.indexOf(t);
        notifyDataSetChanged();
    }


    public void enviarSharePreferences(Animation a1, Animation a2, Animation a3, Animation a4, Animation a5) {

        // Actualizas los valores de las variables
        animacionTransicion = a1;
        animacionDesplazar = a2;
        animacionFlash = a3;
        animacionAparece = a4;
        animacionDesaparece = a5;

    }

}