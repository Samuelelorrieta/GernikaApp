package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Letra {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "idLetra")
    public int idLetra;

    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;

    public Letra(int idLetra, String nombre){

        this.idLetra=idLetra;
        this.nombre=nombre;
    }


}
