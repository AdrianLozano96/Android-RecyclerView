package edu.programacion.tododialogos;

/**
 * class donde tendremos:
 * Constructor
 * metodo para grabar datos al disco (save)
 * metodo para leer datos del disco  (load)
 * En los dos metodos utilizamos la libreria GSON para pasar los datos
 * al formato JSON
 */

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializar {

    //declaracion de variable
    ArrayList<Nota> listNota;

    private String mFilename; // nombre del fichero JSON que se va a guardar la lista
    private Context mContext; // contexto de donde se van a guardar el fichero JSON

    //crear el constructor con el nombre del fichero y el contexto
    public JsonSerializar(String mFilename, Context mContext) {
        this.mFilename = mFilename;
        this.mContext = mContext;
    }

    //metodo para guardar la lista
    //el metodo tendra excepciones que deberemos tratarlas

    public  void save (List<Nota> listaNota)  throws IOException{
    //1.- Pasa objeto (nota) a JSON - proceso de serializacion utilizando la libreria Gson
    //no olvidar importar la libreria (build.gradle (Module))

    //creamos un objeto GSON
    Gson gson = new Gson();

    //pasar la lista a tipo JSON
    String json = gson.toJson(listNota);

    //2.-Grabar en disco el fichero con los datos JSON
    //JSON es un string y para guardar utilizaremos un writer
        Writer writer = null;

        try{
            // output Stream abre el fichero donde guardaremos el JSON
            OutputStream out = mContext.openFileOutput(mFilename, mContext.MODE_PRIVATE);

            // el escritor ya sabe donde escribir su contenido (fichero JSON)
            writer = new OutputStreamWriter(out);

            //el escritor escribe en el disco el array pasado a JSON ( escribe un string)
            writer.write(json);

        } finally {
            //esta accion se realiza siempre

            // si el writer ha podido abrir el fichero, es importante que lo cierre para que no se corronpa
            if (writer != null){
                writer.close();
            }
        }

    }


    //metodo para leer el JSON y pasarlo a lista

    public ArrayList<Nota> load () throws IOException{

        //1.- leer el JSON del disco
        //Buffered reader para leer los datos JSON
        BufferedReader reader = null;
        try {
            //input Stream abre el fichero JSON que vamos a leer y procesar
            InputStream in = mContext.openFileInput(mFilename);
            // el lector, ya sabe de donde leer los datos, de que fichero JSON
            reader = new BufferedReader(new InputStreamReader(in));

            //2.- Pasar lo leido a objeto, utilizando la libreria GSON
            //creamos un objeto Gson
            Gson gson = new Gson();

            //indicamos el tipo de dato a convertir
            Type type = new TypeToken<ArrayList<Nota>>(){} .getType();

            // convertir
            listNota = gson.fromJson(reader,type);
            // ya tenemos el array de notas con todos los objetos de la clase Nota...

        } catch (FileNotFoundException e){
            // la primera  vez nos va a dar error, por que el fichero de notas no existe
            // vamos a crear la lista para evitar el error en el adaptador
            listNota = new ArrayList<Nota>();
        } finally {
            // cerrar el fichero
            if(reader != null){
                reader.close();
            }
        }
        // el metodo retorna la lista
        return  listNota;
    }

}
