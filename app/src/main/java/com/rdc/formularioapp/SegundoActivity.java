package com.rdc.formularioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {

    private String nombreRe, apellidoRe, movilRe, generoRe;
    private TextView tvdatos;
    private Button btnllamar, btnmensaje, btncamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        btnllamar = findViewById(R.id.btnllamar);
        btnmensaje = findViewById(R.id.btnmensaje);
        btncamara = findViewById(R.id.btncamara);

        tvdatos = findViewById(R.id.tvdatos);
        nombreRe = getIntent().getStringExtra("nombre");
        apellidoRe = getIntent().getStringExtra("apellido");
        movilRe = getIntent().getStringExtra("movil");
        generoRe = getIntent().getStringExtra("radiobutton");

        tvdatos.setText("Nombre: " + nombreRe + "\nApellido: " + apellidoRe + "\nMóvil: " + movilRe + "\nGénero: " + generoRe);


        btnllamar.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: " + movilRe));

            if(intent.resolveActivity(getPackageManager()) != null){

                startActivity(intent);
            }

        });

        btnmensaje.setOnClickListener(view -> {

            // Para mandar sms pasando por la app de mensajeria
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + movilRe));
            if(intent.resolveActivity(getPackageManager()) != null){

                startActivity(intent);
            }

            //Para mandar sms sin pasar por la app de mensajeria, directamente lo manda al numero indicado
            /*SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(movilRe,null,"Mensaje prueba app",null,null);*/

        });

        btncamara.setOnClickListener(view -> {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }

        });

    }

}