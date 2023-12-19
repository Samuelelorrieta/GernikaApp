package com.example.gernikaapp.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pregunta {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "pregunta")
    public String pregunta;

}