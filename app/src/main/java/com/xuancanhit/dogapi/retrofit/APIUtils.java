package com.xuancanhit.dogapi.retrofit;

public class APIUtils {
    public static final String BASE_URL = "https://dog.ceo/";
    //Get and sent data from server
    public static DataClient getData() {
        return RetrofitClient.getClient(BASE_URL).create(DataClient.class);
    }
}
