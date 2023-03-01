package com.example.notesapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.notesapp.R;
import com.example.notesapp.Repository.NoteRepository;
import com.example.notesapp.db.NotesDao;
import com.example.notesapp.db.NotesDatabase;
import com.example.notesapp.model.Note;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class singe_note_screen extends AppCompatActivity {
    private NotesDao notesDaosing;
    MaterialToolbar singleToolbar;
    EditText notesTitle;
    EditText notesDesc;
    NoteRepository noteRepository;
    String currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singe_note_screen);
        singleToolbar =findViewById(R.id.single_toolbar);
        singleToolbar.setNavigationOnClickListener(v -> onBackPressed());
        notesTitle=findViewById(R.id.notestitle);
        notesDesc=findViewById(R.id.notesdesc);
        setSupportActionBar(singleToolbar);

        noteRepository=NoteRepository.getInstance(this);
        notesDaosing=noteRepository.notesDatabase.notesDao();

        //Current date
        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        currentDate=dateFormat.format(date);

        Intent intent = getIntent();
        notesTitle.setText(intent.getCharSequenceExtra("title"));
        notesDesc.setText(intent.getCharSequenceExtra("description"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("TAGQWD", "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.single_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            Log.d("TAGQWD", "onOptionsItemSelected: ");
            if(item.getItemId() == R.id.saveas) {
                String titleText = notesTitle.getText().toString();
                String descText = notesDesc.getText().toString();
                noteRepository.addNote(new Note(titleText, descText, currentDate));
            }
        return super.onOptionsItemSelected(item);
    }
}