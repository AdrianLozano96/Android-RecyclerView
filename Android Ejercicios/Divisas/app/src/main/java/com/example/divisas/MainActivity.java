package com.example.divisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private double value;
    private TextView jTexto, jDineroLibra, jDineroDolar;
    //private ImageView jPortada, jEu, jSp;
    private EditText jCantidad;
    private Button jCalcular, jReset;
    private double euro, dolar, libra, yen; //variables donde almacenar el resultado


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jTexto = findViewById(R.id.texto);
        jDineroLibra = findViewById(R.id.dineroLibra);
        jDineroDolar = findViewById(R.id.dineroDolar);
        jCantidad = findViewById(R.id.cantidad);
        jCalcular = findViewById(R.id.calcular);
        jReset = findViewById(R.id.reset);

        jCalcular.setOnClickListener(this);
        jReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calcular:  //variable del layout
                double num = Integer.parseInt(jCantidad.getText().toString());
                double resultadoLibra = num;
                jDineroDolar.setText(String.valueOf(num * 1.1298) + "dolar");
                jDineroLibra.setText(String.valueOf(num * 0.8395) + "libras");
                //Poner condiciones dependiendo de la moneda a elegir cambiar los valores de las variables bool según lo elegido
                //Poner un selector en el layout
                break;

            case R.id.reset:    //variable del layout
                value = 0;
                jDineroLibra.setText("");
                jDineroDolar.setText("");
                jCantidad.setText("");
                break;

        }

    }
}