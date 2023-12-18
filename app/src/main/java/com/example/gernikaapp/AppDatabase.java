package com.example.gernikaapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(
        entities = {Usuario.class, Pregunta.class, Respuesta.class},
        version= 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();
}
