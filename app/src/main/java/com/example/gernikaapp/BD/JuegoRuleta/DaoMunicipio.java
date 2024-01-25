package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DaoMunicipio {

    @Query("SELECT * FROM Municipio WHERE idLetra = :idLetra")
    Municipio obtenerMunicipioPorIdLetra(int idLetra);

    @Insert
    void insertarMunicipio(Municipio municipio);

    @Insert
    void insertarLetra(Letra Letra);

}
