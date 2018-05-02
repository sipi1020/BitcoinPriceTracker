package com.sipi1020.bitcoinpricetracker.ui.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    Button startPicker;

    @BindView(R.id.endPicker)
    Button endPicker;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Date start;
    Date end;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;

    public MainFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this,view);

        presenter.initDatePickers();
        presenter.showDefaultDates(new Date(System.currentTimeMillis() - 7*24*60*60*1000),
                new Date(System.currentTimeMillis()));

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);



        return view;
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
    public void reloadList(PricesResult records) {
        Logger.getLogger("pricesFragmern").log(Level.ALL,"Records received");
        // specify an adapter (see also next example)
        mAdapter = new PricesAdapter(records);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setDefaultDateValues(Date startDate, Date endDate) {
        start = startDate;
        end = endDate;
        updateStartButton();
        updateEndButton();
    }

    @Override
    public void setDateListeners() {
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                start = myCalendar.getTime();
                updateStartButton();
            }

        };

        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                end = myCalendar.getTime();
                updateEndButton();
            }

        };
    }

    @OnClick(R.id.refreshButton)
    public void refresh(){
        presenter.refreshPricesList(start,end);
    }

    @OnClick(R.id.startPicker)
    public void startDateTapped(){
        new DatePickerDialog(getContext(), startDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.endPicker)
    public void endDateTapped(){
        new DatePickerDialog(getContext(), endDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateStartButton() {
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        startPicker.setText(sdf.format(start));

    }

    private void updateEndButton() {
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        endPicker.setText(sdf.format(end));
    }

}
