package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoMunicipio {

    @Query("SELECT * FROM Municipio WHERE idLetra = :idLetra")
    List<Municipio> obtenerMunicipioPorIdLetra(int idLetra);

    @Insert
    void insertarMunicipio(Municipio municipio);

}
