package com.echouaibi.moviesappechouaibi;

import java.io.Serializable;
import java.util.Date;

public class MyMovieData implements Serializable {
    private String title;
    private String overview;
    private String poster_path;
    private int id;
    private Date release_date;
    private Double vote_average;
    public MyMovieData(String title, String overview, String poster_path, int id, Date release_date, Double vote_average) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.id = id;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }
}