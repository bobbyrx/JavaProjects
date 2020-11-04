package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public non-sealed class Movie extends VideoContent implements Streamable{

    private int duration;

    public Movie(String name, Genre genre, PgRating rating, int duration) {
        super(name, genre, rating);
        this.duration = duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String getTitle() {
        return this.getName();
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public PgRating getRating() {
        return this.getPgRating();
    }
}
