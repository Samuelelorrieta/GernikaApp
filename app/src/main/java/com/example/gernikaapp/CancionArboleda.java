package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CancionArboleda extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    //Hace que suene la canción 3 veces
    protected void onStart() {
        super.onStart();
        for(int i=0;i<3;i++) {
            mediaPlayer.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancion_arboleda);

        ImageView tick = findViewById(R.id.tick);
        ImageView tick2 = findViewById(R.id.tick2);
        ImageView tick3 = findViewById(R.id.tick3);
        ImageView tick4 = findViewById(R.id.tick4);
        ImageView tick5 = findViewById(R.id.tick5);
        ImageView tick6 = findViewById(R.id.tick6);
        ImageView tick7 = findViewById(R.id.tick7);
        ImageView tick8 = findViewById(R.id.tick8);
        ImageView tick9 = findViewById(R.id.tick9);

        tick.setVisibility(View.INVISIBLE);
        tick2.setVisibility(View.INVISIBLE);
        tick3.setVisibility(View.INVISIBLE);
        tick4.setVisibility(View.INVISIBLE);
        tick5.setVisibility(View.INVISIBLE);
        tick6.setVisibility(View.INVISIBLE);
        tick7.setVisibility(View.INVISIBLE);
        tick8.setVisibility(View.INVISIBLE);
        tick9.setVisibility(View.INVISIBLE);

        ImageView equis1 = findViewById(R.id.equis1);
        ImageView equis2 = findViewById(R.id.equis2);
        ImageView equis3 = findViewById(R.id.equis3);
        ImageView equis4 = findViewById(R.id.equis4);
        ImageView equis5 = findViewById(R.id.equis5);
        ImageView equis6 = findViewById(R.id.equis6);
        ImageView equis7 = findViewById(R.id.equis7);
        ImageView equis8 = findViewById(R.id.equis8);
        ImageView equis9 = findViewById(R.id.equis9);

        equis1.setVisibility(View.INVISIBLE);
        equis2.setVisibility(View.INVISIBLE);
        equis3.setVisibility(View.INVISIBLE);
        equis4.setVisibility(View.INVISIBLE);
        equis5.setVisibility(View.INVISIBLE);
        equis6.setVisibility(View.INVISIBLE);
        equis7.setVisibility(View.INVISIBLE);
        equis8.setVisibility(View.INVISIBLE);
        equis9.setVisibility(View.INVISIBLE);


        EditText palabra1 = findViewById(R.id.editText1);
        EditText palabra2 = findViewById(R.id.editText2);
        EditText palabra3 = findViewById(R.id.editText3);
        EditText palabra4 = findViewById(R.id.editText4);
        EditText palabra5 = findViewById(R.id.editText5);
        EditText palabra6 = findViewById(R.id.editText6);
        EditText palabra7 = findViewById(R.id.editText7);
        EditText palabra8 = findViewById(R.id.editText8);
        EditText palabra9 = findViewById(R.id.editText9);

        Intent test = new Intent(this, Test.class);
        Button boton = findViewById(R.id.buttonComprobar);

        //Activar Sonido
        mediaPlayer=MediaPlayer.create(this, R.raw.gernikakoarbola);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(palabra1.getText().toString().equals("maitatua")){
                    tick.setVisibility(View.VISIBLE);
                    equis1.setVisibility(View.INVISIBLE);
                    palabra1.setEnabled(false);
                }
                else{
                    palabra1.setText("");
                    equis1.setVisibility(View.VISIBLE);
                }
                if(palabra2.getText().toString().equals("arbola")){
                    tick2.setVisibility(View.VISIBLE);
                    equis2.setVisibility(View.INVISIBLE);
                    palabra2.setEnabled(false);
                }
                else{
                    palabra2.setText("");
                    equis2.setVisibility(View.VISIBLE);
                }
                if(palabra3.getText().toString().equals("Gernikako")){
                    tick3.setVisibility(View.VISIBLE);
                    equis3.setVisibility(View.INVISIBLE);
                    palabra3.setEnabled(false);
                }
                else{
                    palabra3.setText("");
                    equis3.setVisibility(View.VISIBLE);
                }
                if(palabra4.getText().toString().equals("galdu")){
                    tick4.setVisibility(View.VISIBLE);
                    equis4.setVisibility(View.INVISIBLE);
                    palabra4.setEnabled(false);
                }
                else{
                    palabra4.setText("");
                    equis4.setVisibility(View.VISIBLE);
                }
                if(palabra5.getText().toString().equals("zurekin")){
                    tick5.setVisibility(View.VISIBLE);
                    equis5.setVisibility(View.INVISIBLE);
                    palabra5.setEnabled(false);
                }
                else{
                    palabra5.setText("");
                    equis5.setVisibility(View.VISIBLE);
                }
                if(palabra6.getText().toString().equals("bihotzetikan")){
                    tick6.setVisibility(View.VISIBLE);
                    equis6.setVisibility(View.INVISIBLE);
                    palabra6.setEnabled(false);
                }
                else{
                    palabra6.setText("");
                    equis6.setVisibility(View.VISIBLE);
                }
                if(palabra7.getText().toString().equals("jendia")){
                    tick7.setVisibility(View.VISIBLE);
                    equis7.setVisibility(View.INVISIBLE);
                    palabra7.setEnabled(false);
                }
                else{
                    palabra7.setText("");
                    equis7.setVisibility(View.VISIBLE);
                }
                if(palabra8.getText().toString().equals("emanik")){
                    tick8.setVisibility(View.VISIBLE);
                    equis8.setVisibility(View.INVISIBLE);
                    palabra8.setEnabled(false);
                }
                else{
                    palabra8.setText("");
                    equis8.setVisibility(View.VISIBLE);
                }
                if(palabra9.getText().toString().equals("zuzenak")){
                    tick9.setVisibility(View.VISIBLE);
                    equis9.setVisibility(View.INVISIBLE);
                    palabra9.setEnabled(false);
                }
                else{
                    palabra9.setText("");
                    equis9.setVisibility(View.VISIBLE);
                }
                if(tick.getVisibility()==View.VISIBLE&&tick2.getVisibility()==View.VISIBLE&&tick3.getVisibility()==View.VISIBLE&&tick4.getVisibility()==View.VISIBLE&&tick5.getVisibility()==View.VISIBLE&&tick6.getVisibility()==View.VISIBLE&&tick7.getVisibility()==View.VISIBLE&&tick8.getVisibility()==View.VISIBLE&&tick9.getVisibility()==View.VISIBLE) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startActivity(test);
                            mediaPlayer.stop();
                            // Después de la pausa, puedes realizar más operaciones en este hilo
                        }
                    }).start();
                }
            }
        });
    }
}