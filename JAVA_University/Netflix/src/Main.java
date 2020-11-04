import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Episode;
import bg.sofia.uni.fmi.mjt.netflix.content.Movie;
import bg.sofia.uni.fmi.mjt.netflix.content.Series;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;
import bg.sofia.uni.fmi.mjt.netflix.platform.Netflix;
import bg.sofia.uni.fmi.mjt.netflix.platform.StreamingService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Account account1 = new Account("Borislav", LocalDateTime.parse("2000-11-03T12:45:30"));
        Account account2 = new Account("Bobby", LocalDateTime.parse("2005-11-03T12:45:30"));
        Account account3 = new Account("Bob", LocalDateTime.parse("2017-11-03T12:45:30"));
        Account[] accounts = {account1, account2, account3};

        Episode episode1 = new Episode("Beginning", 23);
        Episode episode2 = new Episode("Middle", 20);
        Episode episode3 = new Episode("Ending", 25);
        Episode[] episodes = {episode1, episode2, episode3};

        Streamable stream1 = new Series("Pokemon", Genre.COMEDY, PgRating.G, episodes);
        Streamable stream2 = new Movie("Dracula", Genre.HORROR, PgRating.NC17, 180);
        Streamable stream3 = new Movie("Titanic", Genre.ACTION, PgRating.PG13, 120);
        Streamable[] streams = {stream1, stream2, stream3};

        StreamingService netflix = new Netflix(accounts, streams);

        add(netflix, account1, "Pokemon");
        add(netflix, account1, "Dracula");
        add(netflix, account1, "Titanic");

        //It will catch an runtime exception
        //because account2 is less than 18 years old!!!
        add(netflix, account2, "Dracula");

        try {
            System.out.println("Most viewed: " + netflix.mostViewed().getTitle());
        } catch (NullPointerException ex) {
            System.out.println("Most viewed: none");
        }

        System.out.println("Most viewed: " + netflix.totalWatchedTimeByUsers());
    }

    public static void add(StreamingService stream, Account account, String name) {
        try {
            stream.watch(account, name);
        } catch (ContentUnavailableException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
