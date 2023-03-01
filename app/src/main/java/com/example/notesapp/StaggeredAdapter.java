package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notesapp.Views.singe_note_screen;
import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {
    Context context;
    List<Note> notes;
    public StaggeredAdapter(Context context, List<Note> notes){
        this.context=context;
        this.notes=notes;
    }
    @NonNull
    @Override
    public StaggeredAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleText.setText(notes.get(position).getTitle());
        holder.descripText.setText(notes.get(position).getContent());
        holder.dateText.setText(notes.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText;
        private TextView descripText;
        private TextView dateText;
        private CardView singleNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.title);
            descripText=itemView.findViewById(R.id.description);
            dateText=itemView.findViewById(R.id.current_date);
            singleNote=itemView.findViewById(R.id.singlenote);
            singleNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, singe_note_screen.class);
                    intent.putExtra("title",titleText.getText());
                    intent.putExtra("description",descripText.getText());
                    context.startActivity(intent);
                }
            });
        }
    }
}
