package com.sipi1020.bitcoinpricetracker.iteractor;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.iteractor.events.GetPricesEvent;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;
import com.sipi1020.bitcoinpricetracker.networking.PricesApi;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Viki on 2018-04-05.
 */

public class PricesInteractor {

    @Inject
    PricesApi pricesApi;

    public PricesInteractor() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    public void getPrices(Date startDate, Date endDate){
        String start = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        String end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

        GetPricesEvent event = new GetPricesEvent();
        try {Call<PricesResult> call = pricesApi.getPrices(start,end);
            Response<PricesResult> response = call.execute();
            if (response.code() != 200) {
                Logger.getLogger("pricesApi").log(Level.ALL,"Error during call");
            }
            event.setCode(response.code());
            event.setPrices(response.body());
            EventBus.getDefault().post(event);

        } catch (IOException e) {
            e.printStackTrace();
            EventBus.getDefault().post(event);
        }

    }


}
