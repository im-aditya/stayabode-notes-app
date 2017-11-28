package com.stayabode.notes.presenter;

import com.stayabode.notes.contract.AddNoteContract;

/**
 * Created by Aditya on 11/28/2017.
 */

public class AddNotePresenter implements AddNoteContract.PresenterInterface
{
    AddNoteContract.ViewInterface mAddNoteView;

    public AddNotePresenter(AddNoteContract.ViewInterface addNoteView)
    {
        mAddNoteView = addNoteView;
    }

    @Override
    public void onBackButtonClicked()
    {
        mAddNoteView.finishActivity();
    }

    @Override
    public void onSubmitNoteClicked()
    {
        //TODO : save note to storage
        mAddNoteView.finishActivity();
    }
}
