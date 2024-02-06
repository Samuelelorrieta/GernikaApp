package com.example.gernikaapp.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Figura.class,
        parentColumns = "id",
        childColumns = "idFigura")
        //onDelete = ForeignKey.CASCADE)
})
public class Texto {

    public Texto(String texto,String idioma, int idFigura){
        this.texto=texto;
        this.idioma=idioma;
        this.idFigura=idFigura;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;
    @NonNull
    @ColumnInfo(name = "texto")
    public String texto;
    @NonNull
    @ColumnInfo(name = "idioma")
    public String idioma;
    @NonNull
    @ColumnInfo(name = "idFigura")
    public int idFigura;
}
