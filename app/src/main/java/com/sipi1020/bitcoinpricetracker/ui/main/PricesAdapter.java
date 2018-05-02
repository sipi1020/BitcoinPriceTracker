package com.sipi1020.bitcoinpricetracker.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Viki on 2018-05-01.
 */

public class PricesAdapter extends RecyclerView.Adapter<PricesAdapter.ViewHolder>{

    private PricesResult mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvDate;
        public TextView tvPrice;
        public ImageView imArrow;

        public ViewHolder(View v) {
            super(v);
            tvDate = (TextView)v.findViewById(R.id.date);
            tvPrice = (TextView)v.findViewById(R.id.price);
            imArrow = (ImageView)v.findViewById(R.id.arrow);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PricesAdapter(PricesResult myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PricesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.price_record, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        List<PriceRecord> records = mDataset.getPriceRecordList();



        holder.tvDate.setText(records.get(position).getDate());
        holder.tvPrice.setText("$"+ records.get(position).getPrice());
        if (position < records.size()-1) {
            Double previous = records.get(position+1).getPrice();
            if (previous < records.get(position).getPrice()){
                holder.imArrow.setImageResource(R.drawable.up);
            }
            else {
                holder.imArrow.setImageResource(R.drawable.down);
            }
        }
        else {
            holder.imArrow.setImageDrawable(null);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.getPrices().keySet().toArray().length;
    }
}
