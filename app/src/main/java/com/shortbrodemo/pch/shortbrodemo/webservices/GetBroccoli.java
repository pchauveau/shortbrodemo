package com.shortbrodemo.pch.shortbrodemo.webservices;

import android.util.Log;

import com.shortbrodemo.pch.shortbrodemo.event.WebserviceEvent;
import com.shortbrodemo.pch.shortbrodemo.model.Base;
import com.shortbrodemo.pch.shortbrodemo.session.SessionData;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetBroccoli {

    public void GetBroccoli(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InstagramService.ADDRESS_INSTAGRAM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InstagramService service = retrofit.create(InstagramService.class);
        Call<Base> basicJsonObjectInstagramCall = service.getBroloList();

        basicJsonObjectInstagramCall.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                Base base = response.body();
                SessionData.getInstance().setBase(base);

                EventBus.getDefault().post(new WebserviceEvent(true));
            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {
                Log.e("WS Crash", t.getLocalizedMessage());

                EventBus.getDefault().post(new WebserviceEvent(false));
            }
        });
    }

}
