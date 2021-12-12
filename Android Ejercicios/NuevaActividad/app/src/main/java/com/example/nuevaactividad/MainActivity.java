package com.example.nuevaactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nuevaactividad.segun.SegundaActivity;

public class MainActivity extends AppCompatActivity {

    private Button btCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "Nombre";
        int edad = 20;


        btCambio = findViewById(R.id.pados);




        //PASAR DE UNA ACTIVIDAD A OTRA
        //crear el objeto Intent para pasar a otra actividad. El intent necesita dos parámetros
        // (this) actividad de salida y SegundaActivity. class actividad que queremos mostrar.
        Intent intent = new Intent(this, SegundaActivity.class);

        //PASAR DATOS ENTRE ACTIVIDADES
        //Pasar datos a la segunda actividad y se utilizará clave-valor.
        intent.putExtra("USER_NAME", username);
        intent.putExtra("USER_EDAD", edad);
        intent.putExtra("HOMBRE", true);

        btCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        //ejecutar el intent
        //startActivity(intent);

    }
}