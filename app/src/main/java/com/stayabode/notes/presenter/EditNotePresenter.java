package com.stayabode.notes.presenter;

import com.stayabode.notes.contract.EditNoteContract;
import com.stayabode.notes.data.GlobalConstants;

/**
 * Created by Aditya on 11/28/2017.
 */

public class EditNotePresenter implements EditNoteContract.PresenterInterface
{
    private EditNoteContract.ViewInterface mEditNoteView;

    public EditNotePresenter(EditNoteContract.ViewInterface editNoteView)
    {
        mEditNoteView = editNoteView;
    }

    @Override
    public void onSubmitNoteClicked(boolean isNewNote, String title, String content)
    {
        if(isDataValid(title, content))
        {
            if(isNewNote)
            {
                mEditNoteView.addNoteToStorage(title, content);
            }
            else
            {
                mEditNoteView.updateNoteToStorage(title, content);
            }
        }
    }

    @Override
    public boolean isDataValid(String title, String content)
    {
        if(title != null && title.length() == 0)
        {
            mEditNoteView.invalidInput(GlobalConstants.InvalidData.TITLE);
            return false;
        }

        if(content != null && content.length() == 0)
        {
            mEditNoteView.invalidInput(GlobalConstants.InvalidData.CONTENT);
            return false;
        }

        return true;
    }

    @Override
    public void onBackButtonClicked()
    {
        mEditNoteView.finishActivity();
    }

    @Override
    public void onDestroy()
    {
        mEditNoteView = null;
    }
}
