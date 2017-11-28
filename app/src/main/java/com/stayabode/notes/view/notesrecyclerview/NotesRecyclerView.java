package com.stayabode.notes.view.notesrecyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

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

    public void setOnNoteClickListener(NotesAdapter.OnNoteClickListener itemClickListener)
    {
        mNotesAdapter.setOnClickListener(itemClickListener);
    }

    public void onNewDataReceived(Cursor cursor)
    {
        mNotesAdapter.setCursor(cursor);
    }
}
