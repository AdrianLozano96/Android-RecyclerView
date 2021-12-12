package com.example.nuevaactividad.segun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nuevaactividad.MainActivity;
import com.example.nuevaactividad.R;

public class SegundaActivity extends AppCompatActivity {

    private Button btCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);



        //La forma de recuperar los datos que ha enviado la primera actividad.
        Intent recuperarIntent = getIntent();

        String user = recuperarIntent.getExtras().getString("USER_NAME");
        int edad = recuperarIntent.getExtras().getInt("USER_EDAD");
        boolean hombre = recuperarIntent.getExtras().getBoolean("HOMBRE");

        Toast.makeText(this,"usuario "+user, Toast.LENGTH_SHORT).show();

        btCambio = findViewById(R.id.parauno);

        Intent intent2 = new Intent(this, MainActivity.class);

        btCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });


    }
}