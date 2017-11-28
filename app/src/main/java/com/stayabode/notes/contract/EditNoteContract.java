package com.stayabode.notes.contract;

/**
 * Created by Aditya on 11/28/2017.
 */

public interface EditNoteContract
{
    interface ViewInterface
    {
        void addNoteToStorage(String title, String content);
        void updateNoteToStorage(String title, String content);

        void finishActivity();
        void invalidInput(int msgId);
    }

    interface PresenterInterface
    {
        boolean isDataValid(String title, String content);
        void onSubmitNoteClicked(boolean isNewNote, String title, String content);
        void onBackButtonClicked();
    }
}
