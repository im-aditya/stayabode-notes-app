package com.stayabode.notes.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aditya on 11/27/2017.
 */

public class Note
{
    private static int count = 0;

    private int id;
    private String title;
    private String content;

    private Date creationDate;
    private Date lastUpdated;

    public Note(String title, String content)
    {
        this.id = count++;

        this.title = title;
        this.content = content;

        creationDate = new Date();
        lastUpdated = new Date();
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
        lastUpdated = new Date();
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
        lastUpdated = new Date();
    }

    public String getCreationDate()
    {
        String date = new SimpleDateFormat("MM-dd-yyyy").format(creationDate);
        return date;
    }

    public long getLastUpdated()
    {
        return lastUpdated.getTime();
    }
}
