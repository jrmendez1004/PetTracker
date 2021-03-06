package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.pettracker.Adapters.DogBreedAdapter;
import com.example.pettracker.DogApiClient;
import com.example.pettracker.EndlessRecyclerViewScrollListener;
import com.example.pettracker.Models.DisplayPets;
import com.example.pettracker.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class AddDogActivity extends AppCompatActivity {
    RecyclerView rvDogBreeds;
    List<DisplayPets> dogBreeds;
    DogBreedAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        dogBreeds = new ArrayList<>();
        rvDogBreeds = (RecyclerView) findViewById(R.id.rvDogBreeds);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvDogBreeds.setLayoutManager(gridLayoutManager);
        adapter = new DogBreedAdapter(this, dogBreeds);
        rvDogBreeds.setAdapter(adapter);

        //Endless Scroll implementation
        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadDogBreeds(page);
            }
        };
        rvDogBreeds.addOnScrollListener(scrollListener);

        loadDogBreeds(0); //Initial load
    }

    public void loadDogBreeds(int page) {
        DogApiClient client = new DogApiClient();
        client.getDogBreeds(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONArray jsonArray = json.jsonArray;
                try {
                    dogBreeds.addAll(DisplayPets.fromJsonArray(jsonArray));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e("AddDogActivity", "Wrong API request", throwable);
            }
        }, page);
    }
}