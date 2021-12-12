package com.example.contar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIABLES
    private int value = 0;  //Valor del textView
    private TextView textView;  //Se crea el TextView
    private Button btnAdd, btnIncre, btnDecre, btnRes, btnVisi, btnTake; //Se crean los botones

    //MËTODO PRINCIPAL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se unen las variables de Java con las del Layout

        textView = findViewById(R.id.contador);
        btnAdd = findViewById(R.id.mas);
        btnRes = findViewById(R.id.menos);
        btnIncre = findViewById(R.id.grande);
        btnDecre= findViewById(R.id.menor);
        btnVisi = findViewById(R.id.esconder);
        btnTake = findViewById(R.id.reset);

        //Accion del botón a cada variable
        btnAdd.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnIncre.setOnClickListener(this);
        btnDecre.setOnClickListener(this);
        btnVisi.setOnClickListener(this);
        btnTake.setOnClickListener(this);

    }

    //Eventos acción que se realizará al pulsar cada botón
    @Override
    public void onClick(View view) {
        //Visualiza el boton pulsado
        Log.i("Main", "On Click: "+view.getId());

        //Menú que indica la acción a realizar según el botón pulsado
        float scale;
        switch(view.getId()){
            case R.id.mas:  //variable del layout
                value++;
                textView.setText(""+value);
                break;

            case R.id.menos:    //variable del layout
                value--;
                textView.setText(""+value);
                break;

            case R.id.reset:    //variable del layout
                value = 0;
                textView.setText(""+value);
                break;

            case R.id.grande:   //variable del layout
                scale = textView.getTextScaleX();   //Coge la escala actual
                scale++;
                textView.setTextScaleX(scale);  //Pone la nueva escala
                break;
            case R.id.menor:    //variable del layout
                scale = textView.getTextScaleX();
                scale--;
                textView.setTextScaleX(scale);
                break;
            case R.id.esconder:     //variable del layout
                if(textView.getVisibility()==View.VISIBLE){
                    textView.setVisibility(View.INVISIBLE);
                    btnVisi.setText("Mostrar");
                }else{
                    textView.setVisibility(View.VISIBLE);
                    btnVisi.setText("Ocultar");
                }
                break;
            //textView y btnVisi son variables de android definidas arriba

        }

    }


}