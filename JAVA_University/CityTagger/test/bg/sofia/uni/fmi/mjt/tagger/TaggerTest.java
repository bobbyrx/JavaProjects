package bg.sofia.uni.fmi.mjt.tagger;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaggerTest {

    private final String text = "Plovdiv's old town is a major tourist attraction. It is the second largest city\n"
            + "in Bulgaria, after the capital ,Sofia.\n"
            + "This one's a given, right? Paris is a place where romance and history play side by side.\n"
            + "You know Amsterdam's oh-so-famous central canal, rightly dubbed a UNESCO World Heritage Site in 2010.\n"
            + "There's nowhere on Earth quite like Venice.\n"
            + "For those desperately seeking cherry blossoms, fear not: Kyoto Botanical\n"
            + "Garden and Philosopher's Path are some of Japan's most popular spots for hanami.\n"
            + "You can't talk about Barcelona without mentioning Antoni Gaudí.\n"
            + "One of the world’s oldest cities is brimming with new life these days, but the appeal\n"
            + "of Athens' beauty is its healthy mix of ancient and modern.\n"
            + "It's almost not fair how pretty Sydney is.\n"
            + "Many consider colonial-era San Miguel de Allende one of Mexico’s prettiest cities, and it’s no wonder.\n"
            + "With some of the best Art Nouveau architecture in Europe, Budapest has no bad angles.\n"
            + "Buenos Aires is often called the \"Paris of South America,\".\n"
            + "You can't walk down a street in Lisbon without spotting something beautiful.\n"
            + "Italy has no shortage of beautiful cities, but Florence remains unrivaled in history,"
            + " art, and architecture.\n";

    private final String textTagged = "<city country=\"Bulgaria\">Plovdiv</city>'s old town is a major "
            + "tourist attraction."
            + " It is the second largest city\n"
            + "in Bulgaria, after the capital ,<city country=\"Bulgaria\">Sofia</city>.\n"
            + "This one's a given, right? <city country=\"France\">Paris</city> is a "
            + "place where romance and history play side by side.\n"
            + "You know <city country=\"United States\">Amsterdam</city>'s oh-so-famous <city "
            + "country=\"United States\">"
            + "central</city> canal, rightly dubbed a UNESCO World Heritage Site in 2010.\n"
            + "There's nowhere on Earth quite like <city country=\"United States\">Venice</city>.\n"
            + "For those desperately seeking cherry blossoms, fear not: <city country=\"Japan\">Kyoto</city>"
            + " Botanical\n"
            + "Garden and Philosopher's Path are some <city country=\"Turkey\">of</city> Japan's <city "
            + "country=\"Czech Republic\">"
            + "most</city> popular spots for hanami.\n"
            + "You can't talk about <city country=\"Venezuela\">Barcelona</city> without mentioning Antoni Gaudí.\n"
            + "One <city country=\"Turkey\">of</city> the world’s oldest cities is brimming with new life these"
            + " days, but the appeal\n"
            + "<city country=\"Turkey\">of</city> <city country=\"United States\">Athens</city>' "
            + "beauty is its healthy mix <city country=\"Turkey\">of</city> ancient and modern.\n"
            + "It's almost not fair how pretty <city country=\"Canada\">Sydney</city> is.\n"
            + "Many consider colonial-era <city country=\"Mali\">San</city> Miguel de <city country=\"Mexico\">"
            + "Allende</city> one <city country=\"Turkey\">of</city> "
            + "<city country=\"Philippines\">Mexico</city>’s prettiest cities, and it’s no wonder.\n"
            + "With some <city country=\"Turkey\">of</city> the <city country=\"Netherlands\">best</city> "
            + "Art Nouveau architecture in Europe, <city country=\"Hungary\">Budapest</city> has no bad angles.\n"
            + "Buenos Aires is often called the \"<city country=\"France\">Paris</city> <city"
            + " country=\"Turkey\">of</city> South America,\".\n"
            + "You can't walk down a street in <city country=\"Portugal\">Lisbon</city> without spotting "
            + "something beautiful.\n"
            + "Italy has no shortage <city country=\"Turkey\">of</city> beautiful cities, but <city "
            + "country=\"United States\">Florence</city> remains unrivaled in history, art, and architecture.\n";

    private final String cityCountry = "Plovdiv,Bulgaria\n"
            + "Sofia,Bulgaria\n"
            + "Paris,France\n"
            + "Amsterdam,United States\n"
            + "Central,United States\n"
            + "Venice,United States\n"
            + "Kyoto,Japan\n"
            + "Of,Turkey\n"
            + "Most,Czech Republic\n"
            + "Barcelona,Venezuela\n"
            + "Athens,United States\n"
            + "Sydney,Canada\n"
            + "San,Mali\n"
            + "Mexico,Philippines\n"
            + "Allende,Mexico\n"
            + "Best,Netherlands\n"
            + "Budapest,Hungary\n"
            + "Lisbon,Portugal\n"
            + "Florence,United States\n";

    private final Tagger tagger = new Tagger(new StringReader(cityCountry));

    @Test(expected = RuntimeException.class)
    public void testTaggerWithNull() {
        Tagger tagger = new Tagger(null);
        tagger.getAllTagsCount();
    }

    @Test
    public void testTaggerToAddListOfCities() {
        Map<String, String> newMap = new LinkedHashMap<>();
        List<String> newList = cityCountry.lines().collect(Collectors.toList());
        for (String str : newList) {
            newMap.put(str.split(",")[0], str.split(",")[1]);
        }
        assertEquals("The made collection should have this value in it!",
                newMap,
                tagger.getListOfCityCountry());
    }


    @Test
    public void testTaggedCities() {
        StringWriter newString = new StringWriter(textTagged.length());
        tagger.tagCities(new StringReader(text), newString);
        assertEquals("There is a problem in taggedCity method!",
                textTagged.lines().collect(Collectors.toList()),
                newString.toString().lines().collect(Collectors.toList()));
    }

    @Test
    public void testCheckForSurroundingSymbols() {
        boolean isTrue = tagger.checkForSurroundingSymbols("aaaPlovdivass", 3, 9);
        boolean isFalse = tagger.checkForSurroundingSymbols("Plovdiv", 0, 6);
        boolean isAlright = isTrue && !isFalse;
        assertTrue("Check for surrounding symbols doesn't work", isAlright);
    }

    @Test
    public void testCountTagForCity() {
        String oldStr = "Plovdiv's";
        String city = "Plovdiv";
        String newStr = tagger.countTagForCity(oldStr, city);
        String expect = "<city country=\"" + tagger.getListOfCityCountry().get(city)
                + "\">" + "Plovdiv" + "</city>'s";
        assertEquals("Count tag for cities doesn't work", newStr, expect);
    }

    @Test
    public void testSortByValue() {
        Map<String, Integer> newMap = new LinkedHashMap<>();
        newMap.put("Of", 1);
        newMap.put("Sure", 10);
        newMap.put("Ok", 5);
        Map<String, Integer> newMapTest = new LinkedHashMap<>();
        newMapTest.put("Sure", 10);
        newMapTest.put("Ok", 5);
        newMapTest.put("Of", 1);
        assertEquals("Sort by value doesn't work", newMapTest, tagger.sortByValue(newMap));
    }

    @Test
    public void testGetNMostTaggedCities() {
        ArrayList<String> newArr = new ArrayList<>();
        newArr.add("Of");
        newArr.add("Paris");
        newArr.add("Plovdiv");
        tagger.tagCities(new StringReader(text), new StringWriter(textTagged.length()));
        assertEquals("Get N Most Tagged Cities doesn't work!",
                newArr, tagger.getNMostTaggedCities(3));
    }

    @Test
    public void testGetAllTagsCount() {
        tagger.tagCities(new StringReader(text), new StringWriter(textTagged.length()));
        assertEquals("Get N Most Tagged Cities doesn't work!",
                27, tagger.getAllTagsCount());
    }
}
