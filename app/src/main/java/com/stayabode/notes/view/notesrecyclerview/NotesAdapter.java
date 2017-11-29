package com.stayabode.notes.view.notesrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stayabode.notes.R;
import com.stayabode.notes.data.model.Note;

import java.util.ArrayList;

/**
 * Created by Aditya on 11/26/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>
{
    private ArrayList<Note> mNoteArrayList;
    private OnNoteClickListener mItemClickListener;

    public NotesAdapter()
    {

    }

    public void setNotesList(ArrayList<Note> noteArrayList)
    {
        mNoteArrayList = noteArrayList;
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
        Note note = mNoteArrayList.get(position);
        holder.setNote(note.getId(), note.getTitle(), note.getContent(), note.getLastUpdated());
    }

    @Override
    public int getItemCount()
    {
        if(mNoteArrayList == null)
            return 0;

        return mNoteArrayList.size();
    }

    public void setOnClickListener(OnNoteClickListener itemClickListener)
    {
        mItemClickListener = itemClickListener;
    }

    public void onDestroy()
    {
        mNoteArrayList = null;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private String mId;
        private TextView mTitleTextView;
        private TextView mContentTextView;
        private TextView mLastUpdateDateTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(mItemClickListener != null)
                        mItemClickListener.onNoteClicked(mId);
                }
            });

            mTitleTextView = itemView.findViewById(R.id.tv_title);
            mContentTextView = itemView.findViewById(R.id.tv_content);
            mLastUpdateDateTextView = itemView.findViewById(R.id.tv_last_update_date);
        }

        public void setNote(String id, String title, String content, String lastUpdateTimestamp)
        {
            mId = id;
            mTitleTextView.setText(title);
            mContentTextView.setText(content);
            mLastUpdateDateTextView.setText(lastUpdateTimestamp.split(" ")[0]);
        }
    }

    public interface OnNoteClickListener
    {
        void onNoteClicked(String noteId);
    }
}
