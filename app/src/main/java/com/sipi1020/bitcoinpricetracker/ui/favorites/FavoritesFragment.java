package com.sipi1020.bitcoinpricetracker.ui.favorites;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Viki on 2018-04-05.
 */

public class FavoritesFragment extends Fragment implements FavoritesScreen {

    @Inject
    FavoritesPresenter presenter;

    public FavoritesFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favorites_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachScreen();
    }

    @Override
    public void reloadData(List<TimeRangeData> data) {

    }
}
