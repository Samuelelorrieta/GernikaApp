package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Letra.class,
    parentColumns = "idLetra",
        childColumns = "idLetra")
})


public class Municipio {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idMunicipio")
    public int idMunicipio;
    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;
    @NonNull
    @ColumnInfo(name = "idLetra")
    public int idLetra;

    public Municipio(String nombre,int idLetra){

        this.nombre=nombre;
        this.idLetra=idLetra;
    }

}
