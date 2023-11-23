package com.example.gernikaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class Puzzle extends AppCompatActivity {

    ImageView img_ComingSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        img_ComingSoon = findViewById(R.id.img_ComingSoon);

        Glide.with(this)
                .asGif()
                .load(R.drawable.comingsoon)
                .into(img_ComingSoon);

        img_ComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irBunker = new Intent(Puzzle.this, Bunker.class);
                startActivity(irBunker);
            }
        });


    }
}