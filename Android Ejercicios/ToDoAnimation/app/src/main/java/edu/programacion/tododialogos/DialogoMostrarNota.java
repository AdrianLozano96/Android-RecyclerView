package edu.programacion.tododialogos;

import android.app.Dialog;
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

public class DialogoMostrarNota extends DialogFragment {

    private Nota mNota;

    //metodo onCreateDialog

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       // return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogoView = inflater.inflate(R.layout.dialogo_mostrar_nota,null);

        TextView textViewTitulo = dialogoView.findViewById(R.id.textView_tarea);
        TextView textViewDescripcion = dialogoView.findViewById(R.id.textView_decripcion);

        textViewTitulo.setText(mNota.getTitulo());
        textViewDescripcion.setText(mNota.getDescripcion());

        ImageView ivImporta = dialogoView.findViewById(R.id.imageView_importa);
        ImageView ivTarea = dialogoView.findViewById(R.id.imageView_tarea);
        ImageView ivIdea = dialogoView.findViewById(R.id.imageView_idea);

        // cada imagen se oculta si la nota no es de ese tipo
        if(!mNota.isImportante()){
            ivImporta.setVisibility(View.GONE);
        }
        if (!mNota.isIdea()){
            ivIdea.setVisibility(View.GONE);
        }
        if(!mNota.isTarea()){
            ivTarea.setVisibility(View.GONE);
        }

        Button btnOk = dialogoView.findViewById(R.id.btn_ok);

        builder.setView(dialogoView)
                .setMessage("Nota");

        //el boton ok simplemente cierra la nota
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    public void mostrarNotaSeleccionada(Nota mTemporal) {
        this.mNota=mTemporal;
    }


    //metodo para mostrar nota seleccionada

}
