package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public non-sealed class Series extends VideoContent implements Streamable {

    private Episode[] episodes;
    private int duration;

    public Series(String name, Genre genre, PgRating rating, Episode[] episodes) {
        super(name, genre, rating);
        this.episodes = episodes;

        for (Episode episode : episodes) {
            duration += episode.duration();
        }
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episode[] episodes) {
        this.episodes = episodes;
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
