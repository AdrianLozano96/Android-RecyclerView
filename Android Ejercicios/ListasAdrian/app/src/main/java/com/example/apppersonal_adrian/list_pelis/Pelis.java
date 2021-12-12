package com.example.apppersonal_adrian.list_pelis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppersonal_adrian.R;

import java.util.ArrayList;
import java.util.List;

public class Pelis extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick{
    private RecyclerView lista;
    private RecyclerAdapter adapter;
    private List<PeList> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelis);
        Toast.makeText(this, "TOP MEJORES PELICULAS", Toast.LENGTH_SHORT).show();
        initVista();
        initValores();
    }

    private void initVista(){
        lista = findViewById(R.id.ap_lista);
    }

    private void initValores() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lista.setLayoutManager(manager);
        elementos = getElementos();
        adapter = new RecyclerAdapter(elementos, this);
        lista.setAdapter(adapter);
    }


    private List<PeList> getElementos() {
        List<PeList> peLists = new ArrayList<>();
        peLists.add(new PeList("Cadena Perpetua", "1994", R.drawable.cadena));
        peLists.add(new PeList("El Padrino", "1972", R.drawable.padrino));
        peLists.add(new PeList("El Padrino II", "1974", R.drawable.padriino));
        peLists.add(new PeList("El Caballero Oscuro", "2008", R.drawable.batman));
        peLists.add(new PeList("12 hombres sin piedad", "1957", R.drawable.piedad));
        peLists.add(new PeList("Lista de Schindler", "1993", R.drawable.schindler));
        peLists.add(new PeList("El retorno del rey", "2003", R.drawable.anillos3));
        peLists.add(new PeList("Pulp Fiction", "1994", R.drawable.pulp));
        peLists.add(new PeList("El Bueno, el feo y el malo", "1966", R.drawable.dolar));
        peLists.add(new PeList("La comunidad del anillo", "2001", R.drawable.anillos1));
        peLists.add(new PeList("El club de la lucha", "1999", R.drawable.club));

        return peLists;
    }

    @Override
    public void itemClick(PeList item) {
        Intent intent = new Intent(this, MostrarPelicula.class);
        intent.putExtra("Peliculas", item);
        startActivity(intent);
    }


}

