package com.example.apppersonal_adrian.list_tareas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.apppersonal_adrian.R;

public class MostrarTarea extends DialogFragment {

    private Tarea mTarea;

    //metodo onCreateDialog

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogoView = inflater.inflate(R.layout.mostrar_tarea,null);

        TextView textViewTitulo = dialogoView.findViewById(R.id.mt_titulo);
        TextView textViewDescripcion = dialogoView.findViewById(R.id.mt_descripcion);

        textViewTitulo.setText(mTarea.getTitulo());
        textViewDescripcion.setText(mTarea.getDescripcion());

        ImageView ivFavoritos = dialogoView.findViewById(R.id.mt_favoritos);

        // cada imagen se oculta si la nota no es de ese tipo
        if(!mTarea.isFavoritos()){
            ivFavoritos.setVisibility(View.GONE);
        }


        Button btnVolver = dialogoView.findViewById(R.id.mt_volver);
        Button btnBorrar = dialogoView.findViewById(R.id.mt_borrar);

        builder.setView(dialogoView)
                .setMessage("Tarea");

        //el boton ok simplemente cierra la nota
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Tareas llamarTareas = (Tareas) getActivity();

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llamarTareas.borrarTarea(mTarea);
                dismiss();
            }
        });



        return builder.create();
    }

    public void mostrarTareaSeleccionada(Tarea mTemporal) {
        this.mTarea = mTemporal;
    }


    //metodo para mostrar nota seleccionada

}

