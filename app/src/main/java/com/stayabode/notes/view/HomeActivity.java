package com.stayabode.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.HomeScreenContract;
import com.stayabode.notes.data.GlobalConstants;
import com.stayabode.notes.data.db.DatabaseService;
import com.stayabode.notes.presenter.HomePresenter;
import com.stayabode.notes.view.notesrecyclerview.NotesAdapter;
import com.stayabode.notes.view.notesrecyclerview.NotesRecyclerView;

public class HomeActivity extends CustomActionBarActivity implements HomeScreenContract.ViewInterface, NotesAdapter.OnNoteClickListener
{
    private NotesRecyclerView mNotesRecyclerView;
    private HomePresenter mHomePresenter;
    private DatabaseService mDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenter(this);
        mDatabaseService = new DatabaseService(this);

        mNotesRecyclerView = this.findViewById(R.id.rv_notes_list);
        mNotesRecyclerView.init(this);
        mNotesRecyclerView.setOnNoteClickListener(this);

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
    protected void onResume()
    {
        super.onResume();
        loadData();
    }

    private void loadData()
    {
        mNotesRecyclerView.onNewDataReceived(mDatabaseService.getAllNotes());
    }

    @Override
    public void onNoteClicked(String noteId)
    {
        mHomePresenter.onNoteCardClick(noteId);
    }

    @Override
    public void navigateToEditNoteActivity()
    {
        Intent intent = new Intent(this, EditNoteActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void navigateToEditNoteActivity(String noteId)
    {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra(GlobalConstants.CLICKED_NOTE_ID, noteId);
        this.startActivity(intent);
    }
}
