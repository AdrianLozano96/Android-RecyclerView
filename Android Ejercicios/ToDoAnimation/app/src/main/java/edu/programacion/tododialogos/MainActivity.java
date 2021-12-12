package edu.programacion.tododialogos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import static edu.programacion.tododialogos.SettingsActivity.RAPIDO;


public class MainActivity extends AppCompatActivity implements  NotaItemClickListener {

    Nota mTemporal;
    //Button btnAdd, btnMostrar;

    ArrayList<Nota> notaList;
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    NotaRecyclerAdapter recyclerAdapter;

    private boolean mSonido;
    private int mAnimacion;
    private SharedPreferences mPrefes;

    private JsonSerializar mJsonSerializar;

    //animaciones
    private Animation mAnimFlash;
    private  Animation mAnimFadeIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        //notaList = new ArrayList<Nota>();
        leerDatosFichero(); //leer los datos del fichero

        // Envias los datos al adaptador


        //instanciar el LayoutManager y cargarlo
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //instanciar el adaptador con los datos de la lista
        recyclerAdapter = new NotaRecyclerAdapter(notaList, this);
        //ejecutamos el adaptador
        recyclerView.setAdapter(recyclerAdapter);
    }


    //leer datos desde el fichero
    private void leerDatosFichero() {

        mJsonSerializar = new JsonSerializar("ToDoList.json", MainActivity.this.getApplicationContext());

        try {
            notaList = mJsonSerializar.load();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //grabar los datos serializable
    private  void salvarDatosFichero(){
        try {
            mJsonSerializar.save(notaList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //El metodo onResumen se llama tanto despues del Oncreate,
    //como cuando volvemos a la actividad despues de pasar por otra
    //vamos a inicializar las animaciones
    @Override
    protected void onResume() {
        super.onResume();

        mPrefes = getSharedPreferences("ToDo APP", MODE_PRIVATE);
        mSonido = mPrefes.getBoolean("sonido",true);
        mAnimacion = mPrefes.getInt("anim opcion", RAPIDO);


        //inicializamos las animaciones
        mAnimFlash = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.flash);
        mAnimFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        if (mAnimacion == RAPIDO){
           mAnimFlash.setDuration(100);
        }else if (mAnimacion == SettingsActivity.LENTO){
             mAnimFlash.setDuration(1000);
        }

        // Envias los datos al adaptador
        recyclerAdapter.enviarSharePreferences(mAnimacion,mSonido,mAnimFlash, mAnimFadeIn);

        recyclerAdapter.notifyDataSetChanged();
    }

    //metodo un pause grabaremos los datos en el fichero
    @Override
    protected void onPause() {
        super.onPause();
        // grabar los datos
        salvarDatosFichero();
    }

    public void crearNuevaNota(Nota newNota) {
        //este metodo recibira una nota creada por el dialogo
       // mTemporal = newNota;

        recyclerAdapter.addNota(newNota);
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
            DialogoNuevaNota dialog = new DialogoNuevaNota();
            //mostramos ese dialogo a traves del manager
            dialog.show(getSupportFragmentManager(),"nota_crear");
        }

        if(item.getItemId() == R.id.action_ajustes){
            //creamos un Intent
            Intent intent = new Intent(this, SettingsActivity.class);
            //arrancar la activity
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onNotaItemClick(int position) {

        //Creamos una nueva instancia de Dialog
        DialogoMostrarNota dialogo = new DialogoMostrarNota();
        //indico al dialogo que nota debe mostrar por pantalla
        mTemporal = notaList.get(position);
        dialogo.mostrarNotaSeleccionada(mTemporal);
        //mostramos el dialogo por pantalla, a traves de un manager
        dialogo.show(getSupportFragmentManager(),"nota_mostrar");

    }
}