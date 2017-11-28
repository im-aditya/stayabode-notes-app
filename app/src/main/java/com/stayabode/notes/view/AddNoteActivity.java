package com.stayabode.notes.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.AddNoteContract;
import com.stayabode.notes.presenter.AddNotePresenter;

/**
 * Created by Aditya on 11/28/2017.
 */

public class AddNoteActivity extends CustomActionBarActivity implements AddNoteContract.ViewInterface
{
    private AddNotePresenter mAddNotePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        mActionBar.setDisplayHomeAsUpEnabled(true);
        TextView titleTextView = mActionBar.getCustomView().findViewById(R.id.tv_activity_title);
        titleTextView.setText(R.string.text_new_note);

        mAddNotePresenter = new AddNotePresenter(this);

        ImageButton submitButton = this.findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAddNotePresenter.onSubmitNoteClicked();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mAddNotePresenter.onBackButtonClicked();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNoteToStorage()
    {

    }

    @Override
    public void finishActivity()
    {
        this.finish();
    }
}
