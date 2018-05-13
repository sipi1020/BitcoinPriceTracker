package com.sipi1020.bitcoinpricetracker.ui.favorites;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.ui.main.PricesAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki on 2018-05-02.
 */

public class FavoriteDetailFragment extends Fragment implements FavoriteDetailScreen{

    @Inject
    FavoriteDetailPresenter presenter;

    @BindView(R.id.recycler_view_fav_detail)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public TimeRangeData data;

    public FavoriteDetailFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.favorites_detail, container, false);
        ButterKnife.bind(this,view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter.loadData(data);

        Tracker mTracker = ((BitcoinPriceTrackerApplication) getActivity().getApplication()).getDefaultTracker();
        mTracker.setScreenName("Favorite Detail Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

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
    public void reloadData(TimeRangeData data) {
        mAdapter = new PricesAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }
}
