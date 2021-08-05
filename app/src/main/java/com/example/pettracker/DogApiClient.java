package com.example.pettracker;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.RequestHeaders;

public class DogApiClient {
    private static final String APIKEY = "b1edb9b7-31bc-4cf9-b333-d9d28725b7db"; //create secret file for this
    private static final String API = "https://api.thedogapi.com/v1/";
    private static final String RESPONSE_LIMIT = "26";

    public DogApiClient() {
    }

    public void getDogBreeds(JsonHttpResponseHandler handler, int page) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String breedsAPI = API + "breeds";
        params.put("limit", RESPONSE_LIMIT);
        params.put("page", page);
        params.put("attached_breed", 0); //Not sure what this is
        RequestHeaders requestHeaders = new RequestHeaders();
        requestHeaders.put("x-api-key", APIKEY);
        client.get(breedsAPI, requestHeaders, params, handler);
    }
}
