package com.stayabode.notes.notesrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stayabode.notes.R;
import com.stayabode.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 11/26/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>
{
    private List<Note> mNotesList;

    public NotesAdapter()
    {
        mNotesList = new ArrayList<>();
    }

    public void setData(List<Note> notesList)
    {
        mNotesList.clear();
        mNotesList.addAll(notesList);

        this.notifyDataSetChanged();
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View gitHubRepoView = inflater.inflate(R.layout.layout_note_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(gitHubRepoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position)
    {
        Note note = mNotesList.get(position);
        holder.setNote(note.getTitle(), note.getContent(), note.getCreationDate());
    }

    @Override
    public int getItemCount()
    {
        return mNotesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTitleTextView;
        private TextView mContentTextView;
        private TextView mLastUpdateDateTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.tv_title);
            mContentTextView = itemView.findViewById(R.id.tv_content);
            mLastUpdateDateTextView = itemView.findViewById(R.id.tv_last_update_date);
        }

        public void setNote(String title, String content, String date)
        {
            mTitleTextView.setText(title);
            mContentTextView.setText(content);
            mLastUpdateDateTextView.setText(date);
        }
    }
}
