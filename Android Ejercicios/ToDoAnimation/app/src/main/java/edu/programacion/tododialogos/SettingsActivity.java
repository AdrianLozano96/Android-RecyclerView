package edu.programacion.tododialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    //variables
    private SharedPreferences mPrefes; // para leer datos guardados en disco
    private SharedPreferences.Editor mEditor; // para escribir datos en las shared

    private boolean mSonido; //para activar / desactivar el sonido

    public static final int RAPIDO = 0; //animaciones rapida
    public static final int LENTO = 1;  // animaciones lentas
    public static final int NADA= 2;  // sin animaciones

     int mAnimacion; //para cambiar el tipo de animacion en la APP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //inicializamos las Share preferences
        mPrefes = getSharedPreferences("ToDo APP", MODE_PRIVATE);

        mEditor = mPrefes.edit(); //formato editable

        //logica de activar y desactivar sonido
        mSonido = mPrefes.getBoolean("sonido",true); // recoger valor del disco, si no hay dato asignamos true

        CheckBox checkBoxSonido = findViewById(R.id.sonido_checkbox);

        //visualizamos la opcion que estaba grabada
        if (mSonido){
            checkBoxSonido.setChecked(true); //usuario quiere musica
        } else{
            checkBoxSonido.setChecked(false);
        }

        //para comprobar si el usuario cambia de opinion
        checkBoxSonido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // si el sonido estaba en marcha lo apagamos
                // si el sonido estaba apagado lo ponemos en marcha
                mSonido = ! mSonido;

                mEditor.putBoolean("sonido", mSonido); //guardamos los datos en disco
                mEditor.commit();
            }
        });


        // logica de la animacion
        mAnimacion = mPrefes.getInt("anim opcion", RAPIDO); //recoger dato en disco

        RadioGroup radioGroup = findViewById(R.id.radio_animacion);

        radioGroup.clearCheck(); //desseleccionar cualquier radio button

        //en funcion de la preferencia del jugador, seleccionar una de las 3 modos de animacion
        switch ( mAnimacion){
            case RAPIDO:
                radioGroup.check(R.id.rb_fast);
                break;
            case LENTO:
                radioGroup.check(R.id.rb_slow);
                break;
            case NADA:
                radioGroup.check(R.id.rb_none);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                //recuperamos el radio button que ha sido seleccionado por el usuario  a traves del chceckedId
                RadioButton rb = radioGroup.findViewById(checkedId);

                //int id = checkedId;

                if(null != rb && checkedId >-1){
                    switch (rb.getId()){
                        case R.id.rb_fast:
                            mAnimacion = RAPIDO;
                            break;
                        case R.id.rb_slow:
                            mAnimacion = LENTO;
                            break;
                        case R.id.rb_none:
                            mAnimacion = NADA;
                            break;

                    }
                    mEditor.putInt("anim opcion",mAnimacion);
                    mEditor.commit();
                }

            }
        });
    }

    // vamos utilizar este metodo para escribir las Share preferentes
    // ya que nos interesa que se grabe cuando mos movemos de la actividad
    // comentamos mEditor.commit()
    @Override
    protected void onPause() {
        super.onPause();
        mEditor.commit();
    }
}