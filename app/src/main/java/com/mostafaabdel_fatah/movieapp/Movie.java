package com.mostafaabdel_fatah.movieapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mostafa AbdEl_Fatah on 7/9/2017.
 */

public class Movie {

    private String movie;
    private int    year;
    private float rating;
    private String duration;
    private String director;
    private String tagline;
    @SerializedName("cast")
    private List<Cast> castList;
    private String image;
    private String trailer;
    private String story;

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMovie() {
        return movie;
    }

    public int getYear() {
        return year;
    }

    public float getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }

    public String getTagline() {
        return tagline;
    }

    public String getImage() {
        return image;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getStory() {
        return story;
    }
    public static  class Cast{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
