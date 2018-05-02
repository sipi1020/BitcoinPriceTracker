package com.sipi1020.bitcoinpricetracker.ui.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Viki on 2018-04-05.
 */

public class AboutFragment extends Fragment implements AboutScreen {

    @Inject
    AboutPresenter presenter;

    @BindView(R.id.aboutText)
    TextView aboutText;

    public AboutFragment() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.about_fragment, container, false);
        ButterKnife.bind(this,view);


        aboutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDeveloperWebsite("https://github.com/sipi1020");
            }
        });
        // Inflate the layout for this fragment
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
    public void openDeveloperSite(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
