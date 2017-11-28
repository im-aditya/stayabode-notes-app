package com.stayabode.notes.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stayabode.notes.contract.NotesAppContract;

/**
 * Created by Aditya on 11/28/2017.
 */

public class DatabaseService
{
    private NotesAppDbHelper mNotesAppDbHelper;
    private SQLiteDatabase mDb;

    private Context mContext;

    public DatabaseService(Context context)
    {
        mContext = context;
        mNotesAppDbHelper = new NotesAppDbHelper(mContext);
    }

    public Cursor getAllNotes()
    {
        mDb = mNotesAppDbHelper.getReadableDatabase();
        Cursor cursor = mDb.query(NotesAppContract.NotesTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                NotesAppContract.NotesTable.COLUMN_NOTE_LAST_UPDATE);
        return cursor;
    }

    public long addNote(String title, String content)
    {
        mDb = mNotesAppDbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NotesAppContract.NotesTable.COLUMN_NOTE_TITLE, title);
        cv.put(NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT, content);
        long id = mDb.insert(NotesAppContract.NotesTable.TABLE_NAME, null, cv);
        mDb.close();
        return id;
    }

    public Cursor getNote(String noteId)
    {
        mDb = mNotesAppDbHelper.getReadableDatabase();
        Cursor cursor = mDb.query(NotesAppContract.NotesTable.TABLE_NAME,
                new String[] { NotesAppContract.NotesTable._ID, NotesAppContract.NotesTable.COLUMN_NOTE_TITLE, NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT, NotesAppContract.NotesTable.COLUMN_NOTE_LAST_UPDATE},
                NotesAppContract.NotesTable._ID + "=?",
                new String[] { noteId },
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public int updateNote(String id, String title, String content)
    {
        mDb = mNotesAppDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesAppContract.NotesTable.COLUMN_NOTE_TITLE, title);
        values.put(NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT, content);

        int returnValue = mDb.update(NotesAppContract.NotesTable.TABLE_NAME,
                values,
                NotesAppContract.NotesTable._ID + "=?",
                new String[] {id});
        mDb.close();
        return returnValue;
    }
}
