package com.echouaibi.moviesappechouaibi;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    String API_KEY="b4c1d04c923c2fbed1876c68def58d5b";
    ImageView posterImage;
    TextView title,release_date,overView,averageNote;
    Button btAnnonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        Intent intent =getIntent();
        MyMovieData movieData =(MyMovieData) intent.getSerializableExtra("movieData");
        posterImage=findViewById(R.id.imageViewPoster);
        title = findViewById(R.id.txtTitle);
        release_date = findViewById(R.id.txtReleaseDate);
        overView = findViewById(R.id.txtOverview);
        averageNote =findViewById(R.id.txtVoteAverage);
        btAnnonce = findViewById(R.id.btTrailer);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/W500/"+movieData.getPoster_path())
                .into(posterImage);
        title.setText(movieData.getTitle());
        release_date.setText(movieData.getRelease_date().toString());
        overView.setText(movieData.getOverview());
        averageNote.setText(movieData.getVote_average().toString());

        btAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieTrailer(movieData.getId());
            }
        });

    }

    private void getMovieTrailer(int movie_id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        Call<TrailerResponse> call = tmdbApi.getMoviesTrailers(movie_id,API_KEY);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Trailer> trailerList = response.body().getTrailers();
                    if(!trailerList.isEmpty()){
                        String trailerKey = trailerList.get(0).getKey();
                        watchYoutubeVideo(trailerKey);
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });
    }

    private void watchYoutubeVideo(String trailerKey) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube"+trailerKey));
            startActivity(intent);
        }catch(ActivityNotFoundException ex){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+trailerKey));
            startActivity(intent);
        }
    }
}