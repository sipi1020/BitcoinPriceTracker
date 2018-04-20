package com.sipi1020.bitcoinpricetracker.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Viki on 2018-03-28.
 */

public class MainFragment extends Fragment implements MainScreen {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.startPicker)
    DatePicker startPicker;

    @BindView(R.id.endPicker)
    DatePicker endPicker;

    Date start;
    Date end;


    public MainFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//        startPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                start  = new Date(year,monthOfYear,dayOfMonth);
//            }
//        });
//        endPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                end  = new Date(year,monthOfYear,dayOfMonth);
//            }
//        });

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.attachScreen(this);
        presenter.showDefaultDates(new Date(System.currentTimeMillis()- 60*60*1000* 24 * 7),
                new Date(System.currentTimeMillis()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachScreen();
    }

    @Override
    public void reloadList(PricesResult records) {
        Logger.getLogger("pricesFragmern").log(Level.ALL,"Records received");
    }

    @Override
    public void setDefaultDateValues(Date startDate, Date endDate) {
        start = startDate;
        end = endDate;
    }

    @OnClick(R.id.refreshButton)
    public void refresh(){
        presenter.refreshPricesList(start,end);
    }
}
