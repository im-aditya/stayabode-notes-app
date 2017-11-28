package com.stayabode.notes.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aditya on 11/27/2017.
 */

public class Note
{
    private String title;
    private String content;

    private Date creationDate;
    private Date lastUpdated;

    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;

        creationDate = new Date();
        lastUpdated = new Date();
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
