package com.example.gernikaapp.BD;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Usuario.class, Pregunta.class, Respuesta.class},
        version= 4
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();

}
