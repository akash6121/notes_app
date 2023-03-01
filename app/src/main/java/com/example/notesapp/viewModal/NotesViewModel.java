package com.example.notesapp.viewModal;

import android.app.Application;
import android.content.Context;

import com.example.notesapp.Repository.NoteRepository;
import com.example.notesapp.model.Note;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {
    private final NoteRepository noteRepository;

    public NotesViewModel(Application application){
        super(application);
        Context context=getApplication().getApplicationContext();
        noteRepository= NoteRepository.getInstance(context);
    }
    public LiveData<List<Note>> getNotes(){
        return noteRepository.getNotes();
    }
    public void addNote(Note note){
        noteRepository.addNote(note);
    }
    public void updateNote(Note note){
        noteRepository.updateNote(note);
    }
    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }
}
