package com.sipi1020.bitcoinpricetracker.ui.favorites;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki on 2018-04-05.
 */

public class FavoritesFragment extends Fragment implements FavoritesScreen {

    @Inject
    FavoritesPresenter presenter;

    @BindView(R.id.recycler_view_fav)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  Handler handler;

    public FavoritesFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        presenter.refrehFavoriteList();

        View view = inflater.inflate(R.layout.favorites_fragment, container, false);
        ButterKnife.bind(this,view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(getActivity(),"Data removed from favorites",Toast.LENGTH_SHORT).show();
            }
        };

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
    public void reloadData(List<TimeRangeData> data) {
        mAdapter = new FavoritesAdapter(data, getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showFavoriteRemoved() {
        Message message = handler.obtainMessage(0);
        message.sendToTarget();
    }
}
