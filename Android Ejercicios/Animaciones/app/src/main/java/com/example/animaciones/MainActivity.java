package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements Animation.AnimationListener, View.OnClickListener{

    Animation animacion, aparece, des_aparece, desaparece, zoomas, zoomenos;
    ImageView imageView;
    TextView textViewStatus;

    Button anim1, anim2, anim3, anim4, anim5, anim6;//, anim7,anim8, anim9;

    SeekBar seekBar;
    TextView textViewSpeed;
    int seekSpeedProgress;

    private void loadAnimation(){
        animacion = AnimationUtils.loadAnimation(this, R.anim.animation);
        animacion.setAnimationListener(this);
        aparece = AnimationUtils.loadAnimation(this, R.anim.aparece);
        aparece.setAnimationListener(this);
        des_aparece = AnimationUtils.loadAnimation(this, R.anim.des_aparece);
        des_aparece.setAnimationListener(this);
        desaparece = AnimationUtils.loadAnimation(this, R.anim.desaparece);
        desaparece.setAnimationListener(this);
        zoomas = AnimationUtils.loadAnimation(this, R.anim.zoomas);
        zoomas.setAnimationListener(this);
        zoomenos = AnimationUtils.loadAnimation(this, R.anim.zoomenos);
        zoomenos.setAnimationListener(this);
    }

    private void loadUi() {
        imageView = findViewById(R.id.androide);
        imageView.setOnClickListener(this);
        textViewStatus = findViewById(R.id.textoInicio);
        textViewStatus.setOnClickListener(this);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnClickListener(this);
        textViewSpeed = findViewById(R.id.speedText);
        textViewSpeed.setOnClickListener(this);

        anim1 = findViewById(R.id.anim1);
        anim1.setOnClickListener(this);
        anim2 = findViewById(R.id.anim2);
        anim2.setOnClickListener(this);
        anim3 = findViewById(R.id.anim3);
        anim3.setOnClickListener(this);
        anim4 = findViewById(R.id.anim4);
        anim4.setOnClickListener(this);
        anim5 = findViewById(R.id.anim5);
        anim5.setOnClickListener(this);
        anim6 = findViewById(R.id.anim6);
        anim6.setOnClickListener(this);
        /*
        anim7 = findViewById(R.id.anim7);
        anim7.setOnClickListener(this);
        anim8 = findViewById(R.id.anim8);
        anim8.setOnClickListener(this);
        anim9 = findViewById(R.id.anim9);
        anim9.setOnClickListener(this);

         */

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int valor_actual, boolean moviUsuario) {
                //detecta movimiento en el seekBar
                seekSpeedProgress = valor_actual;
                textViewSpeed.setText(seekSpeedProgress+"/"+seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Detecta que el usuario empieza a moverlo

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAnimation();
        loadUi();

    }

    @Override
    public void onAnimationStart(Animation animation) {
        //textViewStatus
        //Log.d("Tag","Ha empezado la animación");
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        //Log.d("Tag","Ha acabado la animación");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

        //Log.d("Tag","La animación");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.anim1:
                animacion.setDuration(seekSpeedProgress);
                imageView.startAnimation(animacion);
                break;
            case R.id.anim2:
                aparece.setDuration(seekSpeedProgress);
                imageView.startAnimation(aparece);
                break;
            case R.id.anim3:
                des_aparece.setDuration(seekSpeedProgress);
                imageView.startAnimation(des_aparece);
                break;
            case R.id.anim4:
                desaparece.setDuration(seekSpeedProgress);
                imageView.startAnimation(desaparece);
                break;
            case R.id.anim5:
                zoomas.setDuration(seekSpeedProgress);
                imageView.startAnimation(zoomas);
                break;
            case R.id.anim6:
                zoomenos.setDuration(seekSpeedProgress);
                imageView.startAnimation(zoomenos);
                break;
        }

    }

}

/*
        TextView tv = findViewById(R.id.textoInicio);
        Animation animation;
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        animation.setAnimationListener(this);
        tv.startAnimation(animation);
     */