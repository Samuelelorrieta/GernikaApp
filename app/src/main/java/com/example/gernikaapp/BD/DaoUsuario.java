package com.example.gernikaapp.BD;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.gernikaapp.BD.Respuesta;
import com.example.gernikaapp.BD.Usuario;

@Dao
public interface DaoUsuario {
    @Query("SELECT * FROM Pregunta WHERE id= :idEnviar")
    Pregunta obtenerPregunta(int idEnviar);

    @Query("SELECT * FROM Respuesta WHERE id= :idEnviar")
    Respuesta obtenerRespuesta(int idEnviar);

    @Query("SELECT * FROM Usuario WHERE id= :idEnviar")
    Usuario obtenerUsuario(int idEnviar);

    @Insert void insertarUsuario(Usuario usuario);

    @Query("UPDATE Usuario set contrasenya= :nueva_contrasenya")
    void actualizarContra(String nueva_contrasenya);

    @Query("DELETE FROM Usuario WHERE id= :idUsuario")
    void borrarUsuario(int idUsuario);
}
