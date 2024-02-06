package com.example.gernikaapp.BD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gernikaapp.BD.JuegoRuleta.DaoMunicipio;
import com.example.gernikaapp.BD.JuegoRuleta.Letra;
import com.example.gernikaapp.BD.JuegoRuleta.Municipio;

@Database(
        entities = {Usuario.class,Figura.class,Texto.class, Municipio.class, Letra.class},
        version= 4
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();
    public abstract DaoFigura daoFigura();
    public abstract DaoMunicipio daoMunicipio();


    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "Gernika").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


