package com.sipi1020.bitcoinpricetracker.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.presenter.MainPresenter;
import com.sipi1020.bitcoinpricetracker.view.screen.MainScreen;

import java.util.Date;
import java.util.List;

/**
 * Created by Viki on 2018-03-28.
 */

public class MainFragment extends Fragment implements MainScreen {

    MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void reloadList(List<PriceRecord> records) {

    }

    @Override
    public void setDefaultDateValues(Date startDate, Date endDate) {

    }
}
