package edu.programacion.turnocursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //variables
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox checkBoxJava, checkBoxAndroid, checkBoxHtml, checkBoxAngular, checkBoxJavaS, checkBoxUnity;
    TextView textViewTotal;

    int precio = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkBoxAndroid = findViewById(R.id.checkBox_android);
        checkBoxJava = findViewById(R.id.checkBox_java);
        checkBoxHtml = findViewById(R.id.checkBox_html);
        checkBoxAngular = findViewById(R.id.checkBox_angular);
        checkBoxJavaS = findViewById(R.id.checkBox_javas);
        checkBoxUnity = findViewById(R.id.checkBox_unity);

        checkBoxAngular.setOnCheckedChangeListener(this);
        checkBoxJava.setOnCheckedChangeListener(this);
        checkBoxJavaS.setOnCheckedChangeListener(this);

        textViewTotal = findViewById(R.id.textview_total);


        //conexion
        radioGroup = findViewById(R.id.rg_turno);

        //trabajo con una clase anomina que se ejecuta en un hilo secundario
        //Ocultaremos o mostraremos  cursos en funcion del turno elegido
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int isCheched) {

                Log.i("radio", "la seleccion es  " + isCheched);

                radioButton = radioGroup.findViewById(isCheched);

                switch (radioButton.getId()) {
                    case R.id.rb1:

                        Log.i("radio", "el dato elegido es mañana");
                        precio = 0;




                        break;
                    case R.id.rb2:

                        Log.i("radio", "el dato elegido es tarde");
                        //cursos del tuno de tarde
                        checkBoxAngular.setVisibility(View.VISIBLE);
                        checkBoxJava.setVisibility(View.VISIBLE);
                        checkBoxUnity.setVisibility(View.VISIBLE);
                        checkBoxJavaS.setVisibility(View.GONE);
                        checkBoxHtml.setVisibility(View.VISIBLE);

                        break;
                    case R.id.rb3:
                        checkBoxHtml.setVisibility(View.GONE);
                        checkBoxAngular.setVisibility(View.GONE);
                        checkBoxUnity.setVisibility(View.GONE);

                        break;

                }
            }
        });
    }


    //controlar los checbox
    //implemento la interface CompoundButton.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isCheched) {

        //visualizar el view pulsado
        Log.i("radio", "selecciondddddddd" + compoundButton.getId());

        //comprobar cada chechbox con un if por no ser excluyente
        //incremento precio
        //desactivo el checbox
        if (compoundButton.getId() == R.id.checkBox_angular) {
            precio = precio + 300;
            compoundButton.setEnabled(false);
        }

        //aqui sumo o resto en funcion de la seleccion
        if (compoundButton.getId() == R.id.checkBox_java) {
            if (compoundButton.isChecked()) {
                precio = precio + 200;
            }else {
                    precio = precio -200;
                }

        }

        if (compoundButton.getId() == R.id.checkBox_javas) {
            precio = precio + 100;
        }


        //muestro el precio total de todos los cursos seleccionado
        textViewTotal.setText("Total: "+precio);

    }


}