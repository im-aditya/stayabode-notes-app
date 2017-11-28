package com.stayabode.notes.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.stayabode.notes.R;
import com.stayabode.notes.model.Note;
import com.stayabode.notes.notesrecyclerview.NotesRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends CustomActionBarActivity
{

    private NotesRecyclerView mNotesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNotesRecyclerView = this.findViewById(R.id.rv_notes_list);
        mNotesRecyclerView.init(this);

        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note("First note", content));
        noteList.add(new Note("Second note", "Content goes here"));
        noteList.add(new Note("Third note", content));
        mNotesRecyclerView.onNewDataReceived(noteList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}
