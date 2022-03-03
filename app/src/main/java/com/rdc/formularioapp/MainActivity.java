package com.rdc.formularioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnReset, btnEnviar;
    private EditText etNombre, etApellido, etMovil;
    private RadioButton rbh, rbm;

    public static final String KEY_NOMBRE = "key_nombre";
    public static final String KEY_APELLIDO = "key_apellido";
    public static final String KEY_MOVIL = "key_movil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PEDIR PERMISOS

        //Sí este permiso no esta ya permitido de antes, me pide el permiso
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);

        }


        btnReset = findViewById(R.id.btnreset);
        btnEnviar = findViewById(R.id.btnenviar);
        etNombre = findViewById(R.id.etnombre);
        etApellido = findViewById(R.id.etapellido);
        etMovil = findViewById(R.id.etnumero);
        rbh = findViewById(R.id.rbhombre);
        rbm = findViewById(R.id.rbmujer);


        btnReset.setOnClickListener(view -> {

            if(etNombre.getText().toString().isEmpty() && etApellido.getText().toString().isEmpty()
            && etMovil.getText().toString().isEmpty()){

                Toast.makeText(this, "No hay nada escrito en los campos", Toast.LENGTH_SHORT).show();
            }
                etNombre.setText("");
                etApellido.setText("");
                etMovil.setText("");
        });

        btnEnviar.setOnClickListener(view -> {

            Intent intent = new Intent(this, SegundoActivity.class);
            intent.putExtra("nombre",etNombre.getText().toString());
            intent.putExtra("apellido", etApellido.getText().toString());
            intent.putExtra("movil", etMovil.getText().toString());

            startActivity(intent);

        });
    }

    public void comprobarRadioButton(View view){

        Intent intent = new Intent(this, SegundoActivity.class);
        CharSequence nameRB = "";

        switch (view.getId()){

            case R.id.rbhombre:

                if(rbh.isChecked()){

                 nameRB = rbh.getText().toString();
                 intent.putExtra("radiobutton", nameRB);
                    break;

                }

            case R.id.rbmujer:

                if(rbm.isChecked()){

                     nameRB = rbm.getText().toString();
                    intent.putExtra("radiobutton", nameRB);

                    break;

                }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_NOMBRE,etNombre.getText().toString());
        outState.putString(KEY_APELLIDO,etApellido.getText().toString());
        outState.putString(KEY_MOVIL,etMovil.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){

            String n,a,m;
            n = savedInstanceState.getString(KEY_NOMBRE);
            a = savedInstanceState.getString(KEY_APELLIDO);
            m = savedInstanceState.getString(KEY_MOVIL);

            etNombre.setText(n + "");
            etApellido.setText(a + "");
            etMovil.setText(m + "");
        }
    }
}