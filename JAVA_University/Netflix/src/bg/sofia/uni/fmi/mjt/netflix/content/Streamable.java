package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public interface Streamable {

    String getTitle();
    int getDuration();
    PgRating getRating();

}
