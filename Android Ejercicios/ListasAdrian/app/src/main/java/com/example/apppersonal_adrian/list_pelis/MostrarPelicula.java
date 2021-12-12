package com.example.apppersonal_adrian.list_pelis;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppersonal_adrian.R;

public class MostrarPelicula extends AppCompatActivity {
    private ImageView p_caratula;
    private TextView p_titulo;
    private TextView p_anio;
    private PeList miPeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_pelicula);
        setTitle(getClass().getSimpleName());
        initVista();
        initValores();
    }

    private void initVista() {
        p_caratula = findViewById(R.id.mp_caratula);
        p_titulo = findViewById(R.id.mp_titulo);
        p_anio = findViewById(R.id.mp_anio);
    }

    private void initValores(){
        miPeli = (PeList) getIntent().getExtras().getSerializable("Peliculas");
        p_caratula.setImageResource(miPeli.getCaratula());
        p_titulo.setText(miPeli.getTitulo());
        p_anio.setText(miPeli.getAnio());
    }
}
