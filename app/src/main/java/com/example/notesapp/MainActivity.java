package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.notesapp.Views.singe_note_screen;
import com.example.notesapp.model.Note;
import com.example.notesapp.viewModal.NotesViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Note> notes=new ArrayList<>();
    private NotesViewModel notesViewModel;
    ImageView emptyImage;
    RecyclerView notesView;
    StaggeredAdapter staggeredAdapter;
    LinearLayout addNotebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyImage=findViewById(R.id.emptyimg);
        notesView=findViewById(R.id.recyclerView);
        addNotebtn=findViewById(R.id.addnotebtn);

        notesViewModel=new ViewModelProvider(this).get(NotesViewModel.class);

        //Stggered View
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        notesView.setLayoutManager(staggeredGridLayoutManager);
        staggeredAdapter=new StaggeredAdapter(MainActivity.this,notes);
        notesView.setAdapter(staggeredAdapter);
        observeChange();

        addNotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNext();
            }

            private void gotoNext() {
                Intent intent=new Intent(MainActivity.this, singe_note_screen.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void observeChange() {
        Log.d("TAGS", "observeChange: called it");
        notesViewModel.getNotes().observe(this, tempNotes -> {
            if(tempNotes!=null && !tempNotes.isEmpty()){
                notes.addAll(tempNotes);
                emptyImage.setVisibility(View.INVISIBLE);
                staggeredAdapter.notifyDataSetChanged();
            }
            else {
                Log.d("TAGS", "observeChange: notes is not there");
                emptyImage.setVisibility(View.VISIBLE);
            }
        });
    }
}