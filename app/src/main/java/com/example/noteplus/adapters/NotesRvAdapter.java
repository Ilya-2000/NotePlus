package com.example.noteplus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteplus.R;
import com.example.noteplus.databinding.NoteLayoutBinding;
import com.example.noteplus.models.Note;
import com.example.noteplus.ui.all_notes.AllNotesViewModel;
import com.example.noteplus.ui.note.NoteFragment;

import java.util.List;
import java.util.Objects;

public class NotesRvAdapter extends RecyclerView.Adapter<NotesRvAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    private final LayoutInflater inflater;
    private List<Note> noteList;
    private final OnItemClickListener listener;


    public NotesRvAdapter(Context context, List<Note> notes, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.noteList = notes;
        this.listener = listener;
    }

    @Override
    public NotesRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NoteLayoutBinding binding = NoteLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NotesRvAdapter.ViewHolder holder, int position) {

        if (noteList != null) {
            holder.binding.setNote(noteList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(noteList.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        NoteLayoutBinding binding;
        ViewHolder(View view){
            super(view);
            binding = DataBindingUtil.bind(view);

        }

    }
}
