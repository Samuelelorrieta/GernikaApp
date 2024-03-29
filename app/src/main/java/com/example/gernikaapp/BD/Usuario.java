package com.example.gernikaapp.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;
    @NonNull
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "contrasenya")
    public String contrasenya;
    @NonNull
    @ColumnInfo(name = "guardarContra")
    public boolean guardarContra;

    public Usuario(String nombre,String contrasenya,boolean guardarContra){

        this.nombre=nombre;
        this.contrasenya=contrasenya;
        this.guardarContra=guardarContra;
    }
}
