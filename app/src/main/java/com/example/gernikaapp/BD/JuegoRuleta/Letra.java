package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Letra {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idLetra")
    public int idLetra;

    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;

    public Letra(String nombre){
        this.nombre=nombre;
    }


}
