package com.example.gernikaapp.BD.JuegoRuleta;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DaoLetra {
    @Insert
    void insertarLetra(Letra Letra);

}
