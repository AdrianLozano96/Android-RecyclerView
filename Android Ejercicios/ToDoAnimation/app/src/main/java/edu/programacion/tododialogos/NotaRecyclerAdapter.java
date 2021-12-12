package edu.programacion.tododialogos;


import android.annotation.SuppressLint;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotaRecyclerAdapter extends RecyclerView.Adapter<NotaRecyclerAdapter.ViewHolder> {


    //declarar las variables
    ArrayList<Nota> notaList;
    //crear variable de tipo de la interface creada
    final private NotaItemClickListener mOnClickListener; //paso 2

    //generamos el constructor
    public NotaRecyclerAdapter(ArrayList<Nota> notaList, NotaItemClickListener mOnClickListener) {
        this.notaList = notaList;
        this.mOnClickListener = mOnClickListener; // se añade al constructor Paso 3
    }

    //variables para la SharePreferences
    int animationAdapter;
    boolean sonidoAdapter;

    //animaciones
    private Animation animFlastAdapter;
    private  Animation animFadeInAdapter;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //tenemos que inflar el layout donde se muestran los elementos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent,false); //false para que no se destruyan

        // ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //personalizamos la visualizacion

        Nota elementoItem = notaList.get(position);

        Log.i("info", ""+animationAdapter);

        //realizar la animation
       if (elementoItem.isImportante() && animationAdapter!= SettingsActivity.NADA){
            holder.itemView.setAnimation(animFlastAdapter);
        } else {
            holder.itemView.setAnimation(animFadeInAdapter);
        }

        holder.textViewTitulo.setText(notaList.get(position).getTitulo());
        holder.textViewDescripcion.setText(elementoItem.getDescripcion());

        //mostrar las imagenes
        if (! elementoItem.isImportante()){
            holder.imageViewImportante.setVisibility(View.GONE);
        }

        if (! elementoItem.isTarea()){
            holder.imageViewTodo.setVisibility(View.GONE);
        }

        if (! elementoItem.isIdea()){
            holder.imageViewIdea.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        //metodo que devuelve el numero de elementos de la lista
        return notaList.size();
    }

    //elementos del layout
    //paso 4 implementar el onClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //variables
        TextView textViewTitulo, textViewDescripcion;
        ImageView imageViewImportante, imageViewIdea, imageViewTodo;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); //paso 4a agregar el constructor ViewHolder

            //instaciar los objetos
            textViewTitulo = itemView.findViewById(R.id.textView_titulo);
            textViewDescripcion = itemView.findViewById(R.id.textView_decripcion);

            imageViewIdea = itemView.findViewById(R.id.imageView_idea);
            imageViewImportante = itemView.findViewById(R.id.imageView_importa);
            imageViewTodo = itemView.findViewById(R.id.imageView_todo);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onNotaItemClick(position);

        }
    }

    public void addNota (Nota n){
        notaList.add(n);
        notifyDataSetChanged();
    }

    public void enviarSharePreferences(int mAnimacion, boolean mSonido, Animation mAnimFlash, Animation mAnimFadeIn) {

        // Actualizas los valores de las variables
        animationAdapter = mAnimacion;
        sonidoAdapter = mSonido;
        animFlastAdapter = mAnimFlash;
        animFadeInAdapter = mAnimFadeIn;

    }

}
