package com.example.gernikaapp.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gernikaapp.BD.Respuesta;
import com.example.gernikaapp.BD.Usuario;

@Database(
        entities = {Usuario.class, Pregunta.class, Respuesta.class},
        version= 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();
}
