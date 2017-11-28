package com.stayabode.notes.presenter;

import com.stayabode.notes.contract.HomeScreenContract;
import com.stayabode.notes.data.GlobalConstants;

/**
 * Created by Aditya on 11/28/2017.
 */

public class HomePresenter implements HomeScreenContract.PresenterInterface
{
    private HomeScreenContract.ViewInterface mHomeView;

    public HomePresenter(HomeScreenContract.ViewInterface homeView)
    {

        mHomeView = homeView;
    }

    @Override
    public void onFABClick()
    {
        mHomeView.navigateToAddNoteActivity();
    }

    @Override
    public void onNoteCardClick(int noteId)
    {
        mHomeView.navigateToViewActivity(noteId);
    }
}
