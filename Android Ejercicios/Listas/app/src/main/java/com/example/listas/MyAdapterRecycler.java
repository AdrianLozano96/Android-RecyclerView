package com.example.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Hereda de RecyclerView.Adapter<nombreDeLaClase.ClaseInventadaAnidada>
public class MyAdapterRecycler extends RecyclerView.Adapter<MyAdapterRecycler.ViewHolder> {

    //Variables
    List<Animal> animalList;
    //Variable de la interface
    final private ListItenClickInter mOnClickListener;

    //constructor para crear el objeto en el main activity
    public MyAdapterRecycler(List<Animal> animalList, ListItenClickInter mOnClickListener) {
        this.animalList = animalList;
        this.mOnClickListener = mOnClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Indicamos el layout al que le vamos a añadir los valores elementos.
        //Contruyes el Layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_element, parent, false);//false indica que no es la actividad principal.



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Muestra todos los elementos de la lista
        //Carga los datos al holder
        holder.textView.setText(animalList.get(position).getNombre()); //Al tenerlo en el textview lo muestra

    }

    @Override
    public int getItemCount() {


        return animalList.size();   //Devuelve el taamaño de la lista
    }

    //Este seria la relación entre el layout y el java
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {   ////No importa el nombre
        //Variables del layout
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Agrego el constructor
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            //Conecta el layout  item_element con el java
           textView = itemView.findViewById(R.id.elemento);

        }
        //Recoge la posición del item
        @Override
        public void onClick(View view) {
            int posicion = getAdapterPosition();
            mOnClickListener.onListItemClick(posicion);
        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onLongClick(position);
            return true;
        }
    }


}
