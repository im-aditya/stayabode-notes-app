package com.stayabode.notes.data.model;

import java.util.ArrayList;

/**
 * Created by Aditya on 11/27/2017.
 */

public class Note
{
    private static ArrayList<Note> sNotesList = new ArrayList<>();

    private String id;
    private String title;
    private String content;
    private String lastUpdated;

    public Note(String id, String title, String content, String lastUpdated)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.lastUpdated = lastUpdated;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public String getLastUpdated()
    {
        return lastUpdated;
    }

    public void updateNote(String title, String content, String lastUpdated)
    {
        this.title = title;
        this.content = content;
        this.lastUpdated = lastUpdated;
    }

    public static void setNotesList(ArrayList<Note> notesList)
    {
        Note.sNotesList = notesList;
    }

    public static ArrayList<Note> getNotesList()
    {
        return sNotesList;
    }

    public static Note getNoteById(String noteId)
    {
        int len = sNotesList.size();
        for (int i = 0; i < len; i++)
        {
            Note n = sNotesList.get(i);
            if(n.getId().equals(noteId))
            {
                return n;
            }
        }

        return null;
    }

    public static void add(Note note)
    {
        sNotesList.add(note);
    }

    public static void updateNote(String noteId, String title, String content, String lastUpdateTimeStamp)
    {
        getNoteById(noteId).updateNote(title, content, lastUpdateTimeStamp);
    }
}
