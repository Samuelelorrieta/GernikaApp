package com.example.gernikaapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Pregunta.class,
        parentColumns = "id",
        childColumns = "pregunta_id",
        onDelete = ForeignKey.CASCADE)
})
public class Respuesta {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "respuesta")
    public String pregunta;

    @NonNull
    @ColumnInfo(name = "pregunta_id")
    public int pregunta_id;
}
