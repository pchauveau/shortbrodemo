package com.shortbrodemo.pch.shortbrodemo.webservices;

import com.shortbrodemo.pch.shortbrodemo.model.Base;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstagramService {
    String ADDRESS_INSTAGRAM = "https://www.instagram.com";

    @GET("/explore/tags/brocoli/?__a=1")
    Call<Base> getBroloList();
}
