package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear un objeto de tipo SharedPreferences, preferencias guardadas en disco de solo lectura.
        SharedPreferences preferences;

        //Hay que inicializar el objeto. 1º Parámetro nombre del fichero y 2º Parámetro forma de acceso.
        preferences = getSharedPreferences("Shared Preferences App", MODE_PRIVATE);

        //Preferencias de lectura y escritura (versión editable de las Share Preferences)
        SharedPreferences.Editor editor;
        editor = preferences.edit();

        //Se quiere almacenar los siguientes datos en disco
        String username = "Mi usuario";
        int edad = 23;
        boolean terricola = true;

        //Se va a utilizar el editor para guardar las variables en la versión editable.
        editor.putString("username", username);
        editor.putInt("edad", edad);
        editor.putBoolean("es de la Tierra", terricola);


        //Commit guarda toda la información en disco
        editor.commit();

        //Para verse el archivo .xml dirigirse a View -> Tool Windows -> Device File Explorer
        //Ir a la carpeta data -> data -> nombre paquete (com.example.sharedpreferences)

        //preferences.get Sirve para recoger el dato y si no existe indicar el valor.
        String nombreusuario = preferences.getString("username", "nuevo username");




        //Truco que nos viene facilitado por la inicialización de la variable.
        if(username.compareTo("nuevo usuario") == 0){
            //mostrar aquí una pantalla para seleccionar
        }

    }
}