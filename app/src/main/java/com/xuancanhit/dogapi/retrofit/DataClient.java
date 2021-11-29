package com.xuancanhit.dogapi.retrofit;


import androidx.annotation.Nullable;

import com.xuancanhit.dogapi.model.Dog;


import retrofit2.Call;

import retrofit2.http.GET;


public interface DataClient {

    @GET("api/breeds/image/random")
    @Nullable
    Call<Dog> DogData();


}
