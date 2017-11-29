package com.stayabode.notes.view;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.HomeScreenContract;
import com.stayabode.notes.contract.NotesAppContract;
import com.stayabode.notes.data.GlobalConstants;
import com.stayabode.notes.data.db.DatabaseService;
import com.stayabode.notes.data.model.Note;
import com.stayabode.notes.presenter.HomePresenter;
import com.stayabode.notes.view.notesrecyclerview.NotesAdapter;
import com.stayabode.notes.view.notesrecyclerview.NotesRecyclerView;

import java.util.ArrayList;

public class HomeActivity extends CustomActionBarActivity implements HomeScreenContract.ViewInterface, NotesAdapter.OnNoteClickListener, LoaderManager.LoaderCallbacks<ArrayList<Note>>
{
    private static final int NOTES_LOADER_ID = 10;

    private TextView mInfoTextView;
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

        mInfoTextView = this.findViewById(R.id.tv_info);
        mInfoTextView.setVisibility(View.GONE);

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

        //when the activity is created fetching data from the database
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.restartLoader(NOTES_LOADER_ID, new Bundle(), this).forceLoad();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //once the data is being fetched from the database, loading it from a cache
        loadData();
    }

    private void loadData()
    {
        mNotesRecyclerView.onNewDataReceived(Note.getNotesList());

        if(Note.getNotesList().size() == 0)
        {
            showInfo("Add new notes");
        }
        else
        {
            showInfo("");
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();

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

    private void showInfo(String msg)
    {
        if(msg == "")
        {
            mInfoTextView.setVisibility(View.GONE);
            return;
        }

        mInfoTextView.setText(msg);
        mInfoTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<ArrayList<Note>> onCreateLoader(int i, Bundle bundle)
    {
        return new AsyncTaskLoader<ArrayList<Note>>(this)
        {

            @Override
            protected void onStartLoading()
            {
                super.onStartLoading();
                showInfo("Loading notes...");
            }

            @Override
            public ArrayList<Note> loadInBackground()
            {
                ArrayList<Note> noteList = new ArrayList<>();
                Cursor cursor = mDatabaseService.getAllNotes();
                if(cursor.moveToFirst())
                {
                    while (cursor.moveToNext())
                    {
                        String id = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable._ID));
                        String title = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_TITLE));
                        String content = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT));
                        String lastUpdateTimeStamp = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_LAST_UPDATE));
                        noteList.add(new Note(id, title, content, lastUpdateTimeStamp));
                    }
                }

                return noteList;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Note>> loader, ArrayList<Note> data)
    {
        Note.setNotesList(data);
        loadData();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Note>> loader)
    {

    }

    @Override
    protected void onDestroy()
    {
        mHomePresenter.onDestroy();
        mDatabaseService.onDestroy();
        mNotesRecyclerView.onDestroy();

        mHomePresenter = null;
        mDatabaseService = null;
        mNotesRecyclerView = null;

        super.onDestroy();
    }
}
