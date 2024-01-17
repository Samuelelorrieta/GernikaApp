package com.example.gernikaapp.Modelo;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

public class PuzzlePiece extends AppCompatImageView {
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;

    public PuzzlePiece(Context context) {
        super(context);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        // Manejar el clic aquí si es necesario
        return true; // Indica que el clic fue manejado
    }
}