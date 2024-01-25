package com.example.gernikaapp.BD;
import androidx.room.Database;
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

}
