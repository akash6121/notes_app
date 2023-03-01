package com.example.notesapp.Repository;

import android.content.Context;
import android.util.Log;

import com.example.notesapp.db.NotesDatabase;
import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private static NoteRepository instance;
    public NotesDatabase notesDatabase;
    private NoteRepository(Context context){
        notesDatabase=NotesDatabase.getInstance(context);
    }
    public static NoteRepository getInstance(Context context){
        if(instance==null)
            instance= new NoteRepository(context);
        return instance;
    }
    public LiveData<List<Note>> getNotes(){
        return notesDatabase.notesDao().getAllNote();
    }
    public void addNote(Note note){
        Log.d("TAGS", "addNote: working");
        long success=notesDatabase.notesDao().addNote(note);
        Log.d("TAGS", "addNotesdata: "+success);
    }
    public void updateNote(Note note){
        notesDatabase.notesDao().updateNote(note);
    }
    public void deleteNote(Note note){
        notesDatabase.notesDao().deleteNote(note);
    }

}
