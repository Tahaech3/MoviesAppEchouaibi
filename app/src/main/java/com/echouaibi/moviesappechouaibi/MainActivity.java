package com.echouaibi.moviesappechouaibi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String API_KEY="b4c1d04c923c2fbed1876c68def58d5b";
    EditText etSearch;
    Button btSearch;
    List<MyMovieData> moviesList;
    MyMovieAdapter myMovieAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etCherche);
        btSearch = findViewById(R.id.ChercheBout);
        RecyclerView recyclerView =
                findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        Call<MovieResponse> call = tmdbApi.getPopularMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                             moviesList = response.body().getResults();
                             myMovieAdapter = new MyMovieAdapter(moviesList, MainActivity.this);
                             recyclerView.setAdapter(myMovieAdapter);
                         }

                         @Override
                         public void onFailure(Call<MovieResponse> call, Throwable t) {

                         }
                     });


                // MyMovieAdapter myMovieAdapter = new
                //      MyMovieAdapter(myMovieData,MainActivity.this);
                // recyclerView.setAdapter(myMovieAdapter);
    }
}