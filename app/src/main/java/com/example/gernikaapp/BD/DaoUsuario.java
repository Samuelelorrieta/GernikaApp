package com.example.gernikaapp.BD;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DaoUsuario {
    @Query("SELECT * FROM Usuario WHERE id= :idEnviar")
    Usuario obtenerUsuario(int idEnviar);
    @Query("SELECT * FROM Usuario WHERE nombre= :nombree")
    Usuario obtenerUsuarioNombre(String nombree);

    @Insert void insertarUsuario(Usuario usuario);

    @Query("UPDATE Usuario set contrasenya= :nueva_contrasenya WHERE id= :id")
    void actualizarContra(String nueva_contrasenya,int id);

    void actualizarContra(String nueva_contrasenya);

    @Query("DELETE FROM Usuario WHERE id= :idUsuario")
    void borrarUsuario(int idUsuario);
}
