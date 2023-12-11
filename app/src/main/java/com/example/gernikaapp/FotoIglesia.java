package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FotoIglesia extends AppCompatActivity {

    ImageView img_ComingSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_iglesia);
        //Intent irRuleta = new Intent(this, Ruleta.class);

        //Llamamos a la imagen
        img_ComingSoon = findViewById(R.id.img_ComingSoon);

        //Hacemos que se vea el Gif
        Glide.with(this)
                .asGif()
                .load(R.drawable.comingsoon)
                .into(img_ComingSoon);

        //Cuando se toque el Gif que cambie de pantalla
        img_ComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(irRuleta);
            }
        });

    }
}