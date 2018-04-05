package com.sipi1020.bitcoinpricetracker.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Viki on 2018-03-28.
 */

public class MainFragment extends Fragment implements MainScreen {

    @Inject
    MainPresenter presenter;

    public MainFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.main_fragment, container, false);
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
    public void reloadList(List<PriceRecord> records) {

    }

    @Override
    public void setDefaultDateValues(Date startDate, Date endDate) {

    }
}
