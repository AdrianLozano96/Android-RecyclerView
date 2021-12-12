package com.example.apppersonal_adrian.list_tareas;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.apppersonal_adrian.R;

public class NuevaTarea extends DialogFragment {

    private Tareas tareas;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //construir una alert con el constructor Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //solicitamos el inflador
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflamos el dialogo con el layout  dialogo_nueva_nota.xml
        View dialogView = inflater.inflate(R.layout.nueva_tarea,null);

        //instaciomos los elementos del layout
        EditText editTitulo = dialogView.findViewById(R.id.nt_titulo);
        EditText editDescripcion = dialogView.findViewById(R.id.nt_descripcion);

        CheckBox checkBoxFavoritos = dialogView.findViewById(R.id.nt_favoritos);

        Button btnCancelar = dialogView.findViewById(R.id.nt_cancelar);
        Button btnOk = dialogView.findViewById(R.id.nt_aceptar);

        //construimos el dialog
        builder.setView(dialogView)
                .setMessage("Añadir una nueva Tarea");

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tareas = (Tareas) getActivity();
                //crear una nota vacia
                Tarea newTarea = new Tarea();

                // configuramos las 5 varibles de la nota creada
                newTarea.setTitulo(editTitulo.getText().toString());
                newTarea.setDescripcion(editDescripcion.getText().toString());
                //newTarea.setFecha(editFecha.getText().toString());
                newTarea.setFavoritos(checkBoxFavoritos.isChecked());

                if(editTitulo.getText().toString().equals("")){
                    tareas.verificarTarea();
                    dismiss();
                }else{
                    //realizar un casting a MainActivity, que es quien ha llamado al dialogo
                    Tareas llamarTareas = (Tareas) getActivity();
                    //notificaremos a la MA que hemos creado una nueva nota
                    llamarTareas.crearNuevaTarea(newTarea);
                    //cerrar el dialogo
                    dismiss();
                }
            }
        });

        //una vez configurada la alerta, le indicamos al builder que debe crearla
        return builder.create();
    }


}






