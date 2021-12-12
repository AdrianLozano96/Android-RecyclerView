package edu.programacion.tododialogos;

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

public class DialogoNuevaNota  extends DialogFragment {

    //metodo ocCreateDialog

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       // return super.onCreateDialog(savedInstanceState);

        //construir una alert con el constructor Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //solicitamos el inflador
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflamos el dialogo con el layout  dialogo_nueva_nota.xml
        View dialogView = inflater.inflate(R.layout.dialogo_nueva_nota,null);

        //instaciomos los elementos del layout
        EditText editTarea = dialogView.findViewById(R.id.editText_tarea);
        EditText editDescripcion = dialogView.findViewById(R.id.editText_descripcion);

        CheckBox checkBoxIdea = dialogView.findViewById(R.id.checkBox_idea);
        CheckBox checkBoxTarea = dialogView.findViewById(R.id.checkBox_tarea);
        CheckBox checkBoxImport = dialogView.findViewById(R.id.checkBox_importante);

        Button btnCancelar = dialogView.findViewById(R.id.btn_cancelar);
        Button btnOk = dialogView.findViewById(R.id.btn_ok);

        //construimos el dialog
        builder.setView(dialogView)
                .setMessage("Añadir una nueva Nota");

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear una nota vacia
                Nota newNota = new Nota();

                // configuramos las 5 varibles de la nota creada
                newNota.setTitulo(editTarea.getText().toString());
                newNota.setDescripcion(editDescripcion.getText().toString());

                newNota.setIdea(checkBoxIdea.isChecked());
                newNota.setTarea(checkBoxTarea.isChecked());
                newNota.setImportante(checkBoxImport.isChecked());

                //realizar un casting a MainActivity, que es quien ha llamado al dialogo
                MainActivity llamarActivity = (MainActivity) getActivity();

                //notificaremos a la MA que hemos creado una nueva nota
                llamarActivity.crearNuevaNota(newNota);

                //cerrar el dialogo
                dismiss();
            }
        });

        //una vez configurada la alerta, le indicamos al builder que debe crearla
        return builder.create();
    }
}
