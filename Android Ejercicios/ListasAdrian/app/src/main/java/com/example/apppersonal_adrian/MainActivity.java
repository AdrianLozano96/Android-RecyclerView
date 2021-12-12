package com.example.apppersonal_adrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apppersonal_adrian.list_pelis.Pelis;
import com.example.apppersonal_adrian.list_tareas.Tareas;

public class MainActivity extends AppCompatActivity {
    //Variables
    private Button tareasBtn;
    private Button pelisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tareasBtn = findViewById(R.id.am_tareas_btn);
        pelisBtn = findViewById(R.id.am_pelis_btn);

        //PASAR DE UNA ACTIVIDAD A OTRA
        //crear el objeto Intent para pasar a otra actividad. El intent necesita dos parámetros
        // (this) actividad de salida y SegundaActivity. class actividad que queremos mostrar.
        Intent tareasApp = new Intent(this, Tareas.class);
        Intent pelisApp = new Intent(this, Pelis.class);

        //ACCIÓN BOTONES
        tareasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tareasApp);
            }
        });

        pelisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pelisApp);
            }
        });



    }
}