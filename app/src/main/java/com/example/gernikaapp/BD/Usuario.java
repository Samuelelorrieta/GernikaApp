package com.example.gernikaapp.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Usuario {
    @PrimaryKey
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
}
