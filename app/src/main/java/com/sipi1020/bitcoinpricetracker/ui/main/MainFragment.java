package com.sipi1020.bitcoinpricetracker.ui.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

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
    private  Handler handler;

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

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(getActivity(),"Data added to favorites",Toast.LENGTH_SHORT).show();
            }
        };

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        inflater.inflate(R.menu.main, menu);

        Drawable drawable = menu.findItem(R.id.action_favorite).getIcon();

        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(getContext(),R.color.white));
        menu.findItem(R.id.action_favorite).setIcon(drawable);

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            presenter.addToFavorite();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        presenter.refreshPricesList(start,end);
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
                presenter.refreshPricesList(start,end);
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
                presenter.refreshPricesList(start,end);
            }

        };
    }

    @Override
    public void showFavoriteAdded() {
        Message message = handler.obtainMessage(0);
        message.sendToTarget();
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
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        startPicker.setText(sdf.format(start));

    }

    private void updateEndButton() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        endPicker.setText(sdf.format(end));
    }

}
