package com.sipi1020.bitcoinpricetracker.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sipi1020.bitcoinpricetracker.R;
import com.sipi1020.bitcoinpricetracker.view.screen.AboutScreen;

/**
 * Created by Viki on 2018-04-05.
 */

public class AboutFragment extends Fragment implements AboutScreen {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public void openDeveloperSite(String url) {

    }
}
