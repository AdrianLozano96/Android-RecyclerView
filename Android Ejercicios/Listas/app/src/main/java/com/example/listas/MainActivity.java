package com.example.listas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListItenClickInter {

    //Variables
    ArrayList<Animal> animalList;
    RecyclerView recyclerView;
    Animal animal;
    LinearLayoutManager layoutManager;  //Indica como mostrarlo
    MyAdapterRecycler adapterRecycler;  //Adaptador (la otra clase)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Unir Layout principal con Java
        recyclerView = findViewById(R.id.lista);

        //Instancias
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Llenar lista
        llenarLista();

        //Instacia el adaptados con los datos de la lista
        adapterRecycler = new MyAdapterRecycler(animalList, this);

        //Se ejecuta el adaptador
        recyclerView.setAdapter(adapterRecycler);

    }

    private void llenarLista(){
        animalList = new ArrayList<>();
        animalList.add(new Animal("Tigre"));
        animalList.add(new Animal("León"));
        animalList.add(new Animal("Serpiente"));
        animalList.add(new Animal("Escorpión"));
        animalList.add(new Animal("Águila"));
    }


    @Override
    public void onListItemClick(int posicion) {
        Toast.makeText(this," El animal es: "+ animalList.get(posicion).getNombre(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        /*
        Toast.makeText(this," El animal es: "+ animalList.get(position).getNombre(),
                Toast.LENGTH_SHORT).show();
        animalList.remove(position);
        adapterRecycler.notifyDataSetChanged();
        Toast.makeText(this," El animal borrado es: "+ animalList.get(position).getNombre(),
                Toast.LENGTH_SHORT).show();

         */
        dialogoBorrar(position).show();

    }

    public AlertDialog dialogoBorrar(int position){
        //Crear alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Encadenación el dialogo tendrá un titulo msg
        builder.setMessage("Quiere borarr el elemento?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        animal = animalList.get(position);
                        animalList.remove(position);
                        adapterRecycler.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                });
        Toast.makeText(this," El animal borrado es: "+ animalList.get(position).getNombre(),
                Toast.LENGTH_SHORT).show();
        return builder.create();
    }



}