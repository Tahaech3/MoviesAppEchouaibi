package com.echouaibi.moviesappechouaibi;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchMovies(@Query("api_key") String apiKey,@Query("query")String query);

    @GET("movie/popular")
    Call<TrailerResponse> getMoviesTrailers(@Path("movie_id") int movieId, @Query("api_key")String apiKey);
}
