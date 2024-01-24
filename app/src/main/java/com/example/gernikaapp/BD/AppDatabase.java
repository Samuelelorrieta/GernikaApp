package com.example.gernikaapp.BD;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Usuario.class,Figura.class,Texto.class},
        version= 4
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();
    public abstract DaoFigura daoFigura();

}
