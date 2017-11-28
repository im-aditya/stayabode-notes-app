package com.stayabode.notes.contract;

/**
 * Created by Aditya on 11/27/2017.
 */

public interface HomeScreenContract
{
    interface ViewInterface
    {
        void navigateToAddNoteActivity();
        void navigateToViewActivity(int noteId);
    }

    interface PresenterInterface
    {
        void onFABClick();
        void onNoteCardClick(int noteId);
    }
}
