package com.stayabode.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.HomeScreenContract;
import com.stayabode.notes.data.GlobalConstants;
import com.stayabode.notes.presenter.HomePresenter;
import com.stayabode.notes.view.notesrecyclerview.NotesRecyclerView;
import com.stayabode.notes.data.model.Note;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends CustomActionBarActivity implements HomeScreenContract.ViewInterface
{
    private NotesRecyclerView mNotesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final HomePresenter mHomePresenter = new HomePresenter(this);

        mNotesRecyclerView = this.findViewById(R.id.rv_notes_list);
        mNotesRecyclerView.init(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mHomePresenter.onFABClick();
            }
        });
    }

    @Override
    public void navigateToAddNoteActivity()
    {
        Intent intent = new Intent(this, AddNoteActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void navigateToViewActivity(int noteId)
    {
        Intent intent = new Intent(this, ViewNoteActivity.class);
        intent.putExtra(GlobalConstants.CLICKED_NOTE_ID, noteId);
        this.startActivity(intent);
    }
}
