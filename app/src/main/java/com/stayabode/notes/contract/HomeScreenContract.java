package com.stayabode.notes.contract;

/**
 * Created by Aditya on 11/27/2017.
 */

public interface HomeScreenContract
{
    interface ViewInterface
    {
        void navigateToEditNoteActivity();
        void navigateToEditNoteActivity(String noteId);
    }

    interface PresenterInterface
    {
        void onFABClick();
        void onNoteCardClick(String noteId);
    }
}
