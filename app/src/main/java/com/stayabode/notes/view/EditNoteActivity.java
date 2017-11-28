package com.stayabode.notes.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.EditNoteContract;
import com.stayabode.notes.contract.NotesAppContract;
import com.stayabode.notes.data.GlobalConstants;
import com.stayabode.notes.data.db.DatabaseService;
import com.stayabode.notes.presenter.EditNotePresenter;

/**
 * Created by Aditya on 11/28/2017.
 */

public class EditNoteActivity extends CustomActionBarActivity implements EditNoteContract.ViewInterface
{
    private String mNoteId = "";

    private EditNotePresenter mEditNotePresenter;
    private DatabaseService mDatabaseService;

    private EditText mTitleEditView;
    private EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        mActionBar.setDisplayHomeAsUpEnabled(true);

        Intent startingIntent = getIntent();
        Bundle extras = startingIntent.getExtras();
        if(extras != null && extras.getString(GlobalConstants.CLICKED_NOTE_ID) != null)
        {
            mNoteId = extras.getString(GlobalConstants.CLICKED_NOTE_ID);
        }

        TextView titleTextView = mActionBar.getCustomView().findViewById(R.id.tv_activity_title);
        titleTextView.setVisibility(View.GONE);

        mTitleEditView = mActionBar.getCustomView().findViewById(R.id.et_title);
        mTitleEditView.setVisibility(View.VISIBLE);

        mEditNotePresenter = new EditNotePresenter(this);
        mDatabaseService = new DatabaseService(this);

        mContentEditText = this.findViewById(R.id.et_note_input);
        ImageButton submitButton = this.findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mEditNotePresenter.onSubmitNoteClicked(isNewNote(), mTitleEditView.getText().toString(), mContentEditText.getText().toString());
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(!isNewNote() && mTitleEditView.getText().length() == 0)
        {
            Cursor cursor = mDatabaseService.getNote(mNoteId);
            String title = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT));

            mTitleEditView.setText(title);
            mContentEditText.setText(content);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mEditNotePresenter.onBackButtonClicked();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNoteToStorage(String title, String content)
    {
        mDatabaseService.addNote(title, content);
        Toast.makeText(this, getResources().getString(R.string.note_added), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateNoteToStorage(String title, String content)
    {
        mDatabaseService.updateNote(mNoteId, title, content);
        Toast.makeText(this, getResources().getString(R.string.note_updated), Toast.LENGTH_SHORT).show();
    }

    private boolean isNewNote()
    {
        return (mNoteId.length() == 0);
    }

    @Override
    public void invalidInput(int msgId)
    {
        switch (msgId)
        {
            case GlobalConstants.InvalidData.TITLE:
                Toast.makeText(this, getResources().getString(R.string.error_msg_for_empty_title), Toast.LENGTH_SHORT).show();
                break;
            case GlobalConstants.InvalidData.CONTENT:
                Toast.makeText(this, getResources().getString(R.string.error_msg_for_empty_content), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void finishActivity()
    {
        this.finish();
    }
}
