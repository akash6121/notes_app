package com.example.notesapp.db;

import android.content.Context;

import com.example.notesapp.model.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();
    private static final String DB_NAME="notesdb";
    private static NotesDatabase instance;

    public static NotesDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context,NotesDatabase.class,DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

}
