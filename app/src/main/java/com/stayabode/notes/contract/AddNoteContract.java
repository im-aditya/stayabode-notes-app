package com.stayabode.notes.contract;

/**
 * Created by Aditya on 11/28/2017.
 */

public interface AddNoteContract
{
    interface ViewInterface
    {
        void addNoteToStorage();
        void finishActivity();
    }

    interface PresenterInterface
    {
        void onBackButtonClicked();
        void onSubmitNoteClicked();
    }
}
