package com.sipi1020.bitcoinpricetracker.ui.favorites;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.repository.Repository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Viki on 2018-05-02.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{

    private List<TimeRangeData> mDataset;

    @Inject
    Repository repository;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvDate;
        public Button removeButton;

        public ViewHolder(View v) {
            super(v);
            tvDate = (TextView)v.findViewById(R.id.dateRange);
            removeButton = v.findViewById(R.id.removeFav);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FavoritesAdapter(List<TimeRangeData> myDataset) {
        mDataset = myDataset;
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);

        FavoritesAdapter.ViewHolder vh = new FavoritesAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final int pos = position;

        holder.tvDate.setText(mDataset.get(position).getStartDate() + "  -  " + mDataset.get(position).getEndDate() );
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.removeFavorite(mDataset.get(pos));
                notifyDataSetChanged();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
