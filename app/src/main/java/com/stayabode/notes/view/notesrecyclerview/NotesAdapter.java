package com.stayabode.notes.view.notesrecyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stayabode.notes.R;
import com.stayabode.notes.contract.NotesAppContract;

/**
 * Created by Aditya on 11/26/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>
{
    private Cursor mCursor;
    private OnNoteClickListener mItemClickListener;

    public NotesAdapter()
    {

    }

    public void setCursor(Cursor cursor)
    {
        mCursor = cursor;
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
        if(!mCursor.moveToPosition(position))
            return;

        String id = mCursor.getString(mCursor.getColumnIndex(NotesAppContract.NotesTable._ID));
        String title = mCursor.getString(mCursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_TITLE));
        String content = mCursor.getString(mCursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_CONTENT));
        String lastUpdateTimeStamp = mCursor.getString(mCursor.getColumnIndex(NotesAppContract.NotesTable.COLUMN_NOTE_LAST_UPDATE));

        holder.setNote(id, title, content, lastUpdateTimeStamp);
    }

    @Override
    public int getItemCount()
    {
        return mCursor.getCount();
    }

    public void setOnClickListener(OnNoteClickListener itemClickListener)
    {
        mItemClickListener = itemClickListener;
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
            mLastUpdateDateTextView.setText(lastUpdateTimestamp);
        }
    }

    public interface OnNoteClickListener
    {
        void onNoteClicked(String noteId);
    }
}
