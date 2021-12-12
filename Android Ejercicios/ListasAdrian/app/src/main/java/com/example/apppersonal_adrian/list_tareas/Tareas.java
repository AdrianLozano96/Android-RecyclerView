package com.example.apppersonal_adrian.list_tareas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppersonal_adrian.R;

import java.util.ArrayList;

public class Tareas extends AppCompatActivity implements TareaItemClickListener {

    Tarea miTarea;
    ArrayList<Tarea> lisTarea;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TareaRecyclerAdapter recyclerAdapter;
    private JsonSerializar jsonSerial;
    //Animaciones
    private Animation miAnimacionFlash;
    private Animation miAnimacionDesplazar;
    private Animation miAnimacionTransicion;
    private Animation miAnimacionAparece;
    private Animation miAnimacionDesaparece;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        Toast.makeText(this, "APLICACIÓN CON TAREAS PENDIENTES", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recycler);
        leerDatosFichero(); //leer los datos del fichero

        // Envias los datos al adaptador


        //instanciar el LayoutManager y cargarlo
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //instanciar el adaptador con los datos de la lista
        recyclerAdapter = new TareaRecyclerAdapter(lisTarea, this);
        //ejecutamos el adaptador
        recyclerView.setAdapter(recyclerAdapter);


    }

    //leer datos desde el fichero
    private void leerDatosFichero() {

        jsonSerial = new JsonSerializar("LisTareas.json", Tareas.this.getApplicationContext());

        try {
            lisTarea = jsonSerial.load();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //grabar los datos serializable
    private  void salvarDatosFichero(){
        try {
            jsonSerial.save(lisTarea);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //metodo un pause grabaremos los datos en el fichero
    @Override
    protected void onPause() {
        super.onPause();
        // grabar los datos
        salvarDatosFichero();
    }

    public void crearNuevaTarea(Tarea newTarea) {
        //este metodo recibira una nota creada por el dialogo
         miTarea = newTarea;

        recyclerAdapter.addTarea(newTarea);

    }

    public void borrarTarea(Tarea newTarea) {
        //este metodo recibira una nota creada por el dialogo
        avisoBorrarTarea(newTarea).show();
    }

    public AlertDialog avisoBorrarTarea(Tarea newTarea) {
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        aviso.setMessage("Desea borrar esta Tarea?")
                .setTitle("Confirmar Borrado de Tarea")
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        miTarea = newTarea;
                        recyclerAdapter.removeTarea(newTarea);
                    }
                })
                .setNegativeButton("No Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return aviso.create();
    }

    public void verificarTarea(){
        Toast.makeText(this, "Toda tarea deberá de tener almenos un TITULO", Toast.LENGTH_SHORT).show();
    }

    //El metodo onResumen se llama tanto despues del Oncreate,
    //como cuando volvemos a la actividad despues de pasar por otra
    //vamos a inicializar las animaciones
    @Override
    protected void onResume() {
        super.onResume();

        //inicializamos las animaciones
        miAnimacionFlash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.flash);
        miAnimacionTransicion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.transicion);
        miAnimacionDesplazar = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazar);
        miAnimacionAparece = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.aparece);
        miAnimacionDesaparece = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desaparece);

        miAnimacionFlash.setDuration(2500);
        miAnimacionTransicion.setDuration(5000);
        miAnimacionDesplazar.setDuration(5000);
        miAnimacionAparece.setDuration(5000);
        miAnimacionDesaparece.setDuration(5000);
        // Envias los datos al adaptador
        recyclerAdapter.enviarSharePreferences(
                miAnimacionTransicion, miAnimacionDesplazar, miAnimacionFlash,
                miAnimacionAparece, miAnimacionDesaparece);

        recyclerAdapter.notifyDataSetChanged();
    }



    //Trabajar con el menu
    //1.- Crear y inflar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflamos el menu con el layout menu creado
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //2.- Indicar lo que hay que realizar cuando se pulsa la opcion del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.action_add){
            //aqui debemos de invocar una nueva instancia del dialogo para crear notas
            NuevaTarea tarea = new NuevaTarea();
            //mostramos ese dialogo a traves del manager
            tarea.show(getSupportFragmentManager(),"tarea_crear");
        }

        return false;
    }

    @Override
    public void onTareaItemClick(int position) {

        //Creamos una nueva instancia de Dialog
        MostrarTarea dialogo = new MostrarTarea();
        //indico al dialogo que nota debe mostrar por pantalla
        miTarea = lisTarea.get(position);
        dialogo.mostrarTareaSeleccionada(miTarea);
        //mostramos el dialogo por pantalla, a traves de un manager
        dialogo.show(getSupportFragmentManager(),"tarea_mostrar");

    }

}
