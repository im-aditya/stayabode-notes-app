package com.stayabode.notes.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stayabode.notes.contract.NotesAppContract;

/**
 * Created by Aditya on 11/28/2017.
 */

public class NotesAppDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public NotesAppDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                NotesAppContract.NotesTable.TABLE_NAME + " (" +
                NotesAppContract.NotesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesAppContract.NotesTable.COLUMN_NOTE_TITLE + " TEXT NOT NULL, " +
                NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT + " TEXT, " +
                NotesAppContract.NotesTable.COLUMN_NOTE_LAST_UPDATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NotesAppContract.NotesTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
