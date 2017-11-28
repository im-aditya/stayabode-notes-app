package com.stayabode.notes.view.notesrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.stayabode.notes.data.model.Note;

import java.util.List;

/**
 * Created by Aditya on 11/26/2017.
 */

public class NotesRecyclerView extends RecyclerView
{
    private NotesAdapter mNotesAdapter;

    public NotesRecyclerView(Context context)
    {
        super(context);
    }

    public NotesRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void init(Context context)
    {
        this.setHasFixedSize(true);
        this.setLayoutManager(new LinearLayoutManager(context));

        mNotesAdapter = new NotesAdapter();
        this.setAdapter(mNotesAdapter);
    }

    public void onNewDataReceived(List<Note> notesList)
    {
        mNotesAdapter.setData(notesList);
    }
}
