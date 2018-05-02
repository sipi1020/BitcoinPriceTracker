package com.sipi1020.bitcoinpricetracker.iteractor;

import android.content.Context;
import android.os.StrictMode;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.iteractor.events.DeleteFavoriteEvent;
import com.sipi1020.bitcoinpricetracker.iteractor.events.GetFavoritesEvent;
import com.sipi1020.bitcoinpricetracker.iteractor.events.SaveFavoriteEvent;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.networking.FavoritesApi;
import com.sipi1020.bitcoinpricetracker.repository.Repository;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Viki on 2018-04-20.
 */

public class FavoritesInteractor {

    @Inject
    FavoritesApi favoritesApi;

    @Inject
    Repository repository;

    @Inject
    Context context;

    public FavoritesInteractor() {
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    public void getFavorites(){
        GetFavoritesEvent event = new GetFavoritesEvent();
        try {
            Call<List< TimeRangeData >> call = favoritesApi.getFavorites();
            Response<List<TimeRangeData>> response = call.execute();
            if (response.code() != 200) {
                Logger.getLogger("favoriteApi").log(Level.ALL,"Error during call");
            }
            event.setCode(response.code());
            event.setData(response.body());
            EventBus.getDefault().post(event);

        } catch (IOException e) {
            e.printStackTrace();
            EventBus.getDefault().post(event);
        }
    }

    public void saveFavorite(TimeRangeData data){
        SaveFavoriteEvent event = new SaveFavoriteEvent();
        try {
            Call<Void> call = favoritesApi.addFavorite(data);
            Response<Void> response = call.execute();
            if (response.code() != 200) {
                Logger.getLogger("favoriteApi").log(Level.ALL,"Error during call");
            }
            event.setCode(response.code());
            event.setData(null);
            EventBus.getDefault().post(event);

        } catch (IOException e) {
            e.printStackTrace();
            EventBus.getDefault().post(event);
        }
        repository.open(context);
        repository.saveFavorite(data);
        repository.close();

    }

    public void removeFavorite(long id){
        DeleteFavoriteEvent event = new DeleteFavoriteEvent();
        try {
            Call<Void> call = favoritesApi.deleteFavorite(id);
            Response<Void> response = call.execute();
            if (response.code() != 200) {
                Logger.getLogger("favoriteApi").log(Level.ALL,"Error during call");
            }
            event.setCode(response.code());
            event.setData(null);
            EventBus.getDefault().post(event);

        } catch (IOException e) {
            e.printStackTrace();
            EventBus.getDefault().post(event);
        }
        repository.open(context);
        repository.removeFavorite(id);
    }
}
