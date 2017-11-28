package com.stayabode.notes.contract;

import android.provider.BaseColumns;

/**
 * Created by Aditya on 11/28/2017.
 */

public final class NotesAppContract
{
    private NotesAppContract()
    {

    }

    public static class NotesTable implements BaseColumns
    {
        public static final String TABLE_NAME = "notes_table";

        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_CONTENT = "note_content";
        public static final String COLUMN_NOTE_LAST_UPDATE = "note_last_update";
    }
}
