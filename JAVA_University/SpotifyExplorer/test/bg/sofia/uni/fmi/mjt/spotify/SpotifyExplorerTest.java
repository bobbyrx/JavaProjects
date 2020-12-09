package bg.sofia.uni.fmi.mjt.spotify;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SpotifyExplorerTest {

    private final String rawString = "id,artists,name,year,popularity,duration_ms,tempo,loudness,"
            + "valence,acousticness,danceability,energy,liveness,speechiness,explicit\n"
            + "4d6HGyGT8e121BsdKmw9v6,['Phil Regan'],When Irish Eyes Are"
            + " Smiling,1921,2,166693,101.665,-10.096,0.253,0.957,0.418,0.193,0.229,0.038,0\n"
            + "1HaVsdm2IqgleDPCXYscOk,['Steve Wariner'],Life's Highway,1985,35,197240,99.308,"
            + "-14.991,0.555,0.0973,0.66,0.371,0.155,0.0238,0\n"
            + "0Orud39gtgh83Ner8eIMIl,['New Edition'],A Little Bit Of Love,1985,34,249800,99.978,"
            + "-12.115,0.754,0.404,0.685,0.811,0.235,0.0677,0\n"
            + "2F9giG3Sziq3kJJl9n0GJi,['Ignacio Corsini'],"
            + "Lamento - Remasterizado,1921,0,155987,87.417,-20.828,"
            + "0.619,0.956,0.293,0.232,0.109,0.0679,0\n"
            + "7kEskqz1RpjJLdDHzrmhUx,['Ignacio Corsini'],"
            + "Hermano Gaucho - Remasterizado,1946,0,180907,92.895,-16.517,"
            + "0.638,0.989,0.477,0.277,0.109,0.0599,0\n"
            + "5Jnvs9kBpdU70kkqYR7XLt,['Эрих Мария Ремарк'],"
            + "Часть 82.4 & Часть 83.1 - Триумфальная арка,1945,0,109500,"
            + "113.254,-21.753,0.545,0.374,0.717,0.137,0.117,0.928,1\n"
            + "5fqW6tFvvvWwh1DvIM8T0K,['Phil Regan'],A Sweet Irish Sweetheart Of Mine,"
            + "1921,1,189373,93.765,-9.853,0.189,0.952,0.32,0.249,0.12,0.0342,0\n"
            + "1wf9F3L1B11i9WTfvwnfMo,['Great Big Sea'],The Chemical Worker's Song (Process Man),"
            + "1995,47,156267,112.033,-10.445,0.732,0.647,0.827,0.446,0.0891,0.226,0\n"
            + "5OLjhk2db14wAFMmWjrxGw,['Cake'],Frank Sinatra,"
            + "1996,50,241027,114.225,-9.2,0.554,0.184,0.679,0.513,0.177,0.0417,0\n";

    private final byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
    private final String stringTest = new String(bytes, StandardCharsets.UTF_8);


    private final SpotifyExplorer spotifyExplorer = new SpotifyExplorer(new StringReader(stringTest));
    private final SpotifyExplorer spotifyExplorerEmpty = new SpotifyExplorer(new StringReader(""));

    @Test
    public void testGetAllSpotifyTracks() {
        assertEquals("It should not be empty!", 9,
                spotifyExplorer.getAllSpotifyTracks().size());
    }

    @Test
    public void testGetAllSpotifyTracksIfEmpty() {
        assertTrue("It should be empty!",
                spotifyExplorerEmpty.getAllSpotifyTracks().isEmpty());
    }

    @Test
    public void testGetExplicitSpotifyTracks() {
        assertTrue("It should only Include explicit",
                spotifyExplorer.getExplicitSpotifyTracks().stream()
                        .allMatch(SpotifyTrack::explicit));
    }

    @Test
    public void testGetExplicitSpotifyTracksIfEmpty() {
        assertTrue("It should be empty",
                spotifyExplorerEmpty.getExplicitSpotifyTracks().isEmpty());
    }

    @Test
    public void testGroupSpotifyTracksByYear() {
        List<Integer> newList = new ArrayList<>();
        newList.add(1921);
        newList.add(1985);
        newList.add(1995);
        newList.add(1996);
        newList.add(1946);
        newList.add(1945);
        assertTrue("Method groupSpotifyTrackByYear does not work",
                spotifyExplorer.groupSpotifyTracksByYear().keySet().containsAll(newList));
        assertEquals("Method groupSpotifyTrackByYear does not work",
                3,
                spotifyExplorer.groupSpotifyTracksByYear().get(1921).size());
    }

    @Test
    public void testGroupSpotifyTracksByYearIfEmpty() {
        assertTrue("Method groupSpotifyTrackByYear does not work",
                spotifyExplorerEmpty.groupSpotifyTracksByYear().isEmpty());
    }

    @Test
    public void testGetArtistActiveYears() {
        assertEquals("Method getArtistActiveYears does not work", 26,
                spotifyExplorer.getArtistActiveYears("Ignacio Corsini"));
        assertEquals("Method getArtistActiveYears does not work", 1,
                spotifyExplorer.getArtistActiveYears("Эрих Мария Ремарк"));
        assertEquals("Method getArtistActiveYears does not work", 0,
                spotifyExplorer.getArtistActiveYears("None"));
    }

    @Test
    public void testGetTopNHighestValenceTracksFromThe80s() {
        List<SpotifyTrack> newList = new ArrayList<>();

        newList.add((SpotifyTrack.of("0Orud39gtgh83Ner8eIMIl,['New Edition'],"
                + "A Little Bit Of Love,1985,34,249800,99.978,"
                + "-12.115,0.754,0.404,0.685,0.811,0.235,0.0677,0")));
        newList.add(SpotifyTrack.of("1HaVsdm2IqgleDPCXYscOk,['Steve Wariner'],"
                + "Life's Highway,1985,35,197240,99.308,"
                + "-14.991,0.555,0.0973,0.66,0.371,0.155,0.0238,0"));

        assertEquals("Method getTopNHighestValenceTracksFromThe80s does not work!",
                newList,
                spotifyExplorer.getTopNHighestValenceTracksFromThe80s(2));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTopNHighestValenceTracksFromThe80sIfNegative() {
        spotifyExplorer.getTopNHighestValenceTracksFromThe80s(-10);
    }

    @Test
    public void testGetMostPopularTrackFromThe90s() {
        assertEquals("Method getMostPopularTrackFromThe90s does not work!",
                SpotifyTrack.of("5OLjhk2db14wAFMmWjrxGw,['Cake'],Frank Sinatra,"
                        + "1996,50,241027,114.225,-9.2,0.554,0.184,0.679,0.513,0.177,0.0417,0"),
                spotifyExplorer.getMostPopularTrackFromThe90s());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetMostPopularTrackFromThe90sIfEmpty() throws IOException {
        spotifyExplorerEmpty.getMostPopularTrackFromThe90s();
    }

    @Test
    public void testGetNumberOfLongerTracksBeforeYear() {
        assertEquals("Method getTopNHighestValenceTracksFromThe80s does not work!",
                7,
                spotifyExplorer.getNumberOfLongerTracksBeforeYear(2, 1996));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNumberOfLongerTracksBeforeYearIfNegative() {
        spotifyExplorer.getNumberOfLongerTracksBeforeYear(-10, -10);
    }

    @Test
    public void testGetTheLoudestTrackInYear() {
        assertEquals("Method getTheLoudestTrackInYear does not work!",
                SpotifyTrack.of("5fqW6tFvvvWwh1DvIM8T0K,['Phil Regan'],A Sweet Irish Sweetheart Of Mine,"
                        + "1921,1,189373,93.765,-9.853,0.189,0.952,0.32,0.249,0.12,0.0342,0"),
                spotifyExplorer.getTheLoudestTrackInYear(1921).get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTheLoudestTrackInYearIfNegative() {
        spotifyExplorer.getTheLoudestTrackInYear(-10);
    }

}
