package com.example.gernikaapp.BD;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoFigura {
    @Query("Select texto from Texto INNER JOIN Figura ON Texto.idFigura = Figura.id WHERE Figura.nombre= :nombre AND texto.idioma= :idioma" )
    String obtenerTexto(String idioma, String nombre);
    @Insert
    void insertarFigura(Figura figura);
    @Insert
    void insertarTexto(Texto texto);


}
