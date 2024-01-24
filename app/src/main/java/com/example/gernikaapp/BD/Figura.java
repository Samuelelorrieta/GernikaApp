package com.example.gernikaapp.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Figura {

    public Figura(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;
    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;
}
