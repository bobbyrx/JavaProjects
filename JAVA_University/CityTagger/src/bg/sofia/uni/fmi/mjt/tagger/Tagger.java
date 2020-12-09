package bg.sofia.uni.fmi.mjt.tagger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tagger {

    private final Map<String, String> listOfCityCountry;
    private final Map<String, Integer> numberOfTagsForCity;

    public boolean checkForSurroundingSymbols(String line, int firstIndex, int lastIndex) {

        boolean checkFirst;
        try {
            checkFirst = (line.charAt(firstIndex - 1) >= 'A' && line.charAt(firstIndex - 1) <= 'Z')
                    || (line.charAt(firstIndex - 1) >= 'a' && line.charAt(firstIndex - 1) <= 'z');

        } catch (IndexOutOfBoundsException e) {
            checkFirst = false;
        }

        boolean checkLast;
        try {
            checkLast = (line.charAt(lastIndex + 1) >= 'A' && line.charAt(lastIndex + 1) <= 'Z')
                    || (line.charAt(lastIndex + 1) >= 'a' && line.charAt(lastIndex + 1) <= 'z');

        } catch (IndexOutOfBoundsException e) {
            checkLast = false;
        }

        return checkFirst || checkLast;
    }

    public String countTagForCity(String line, String city) {
        String newLine = "";
        while (!line.equals("")) {
            if (!line.toUpperCase().contains(city.toUpperCase())) {
                break;
            }
            int firstIndex = line.toUpperCase().indexOf(city.toUpperCase());
            int lastIndex = firstIndex + city.length();
            if (checkForSurroundingSymbols(line, firstIndex, lastIndex - 1)) {
                newLine += line.substring(0, lastIndex);
            } else {
                newLine += line.substring(0, firstIndex);
                String word = line.substring(firstIndex, lastIndex);
                String newWord = "<city country=\"" + listOfCityCountry.get(city) + "\">" + word + "</city>";
                newLine += newWord;
                if (numberOfTagsForCity.containsKey(city)) {
                    numberOfTagsForCity.put(city, numberOfTagsForCity.get(city) + 1);
                } else {
                    numberOfTagsForCity.put(city, 1);
                }
            }
            line = line.substring(lastIndex);
        }
        newLine += line;
        return newLine;

    }

    public Map<String, Integer> sortByValue(Map<String, Integer> newMap) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(newMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Integer> mapSorted = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> set : list) {
            mapSorted.put(set.getKey(), set.getValue());
        }
        return mapSorted;
    }

    /**
     * Creates a new instance of Tagger for a given list of city/country pairs
     *
     * @param citiesReader a java.io.Reader input stream containing list of cities and countries
     *                     in the specified CSV format
     */
    public Tagger(Reader citiesReader) {
        this.listOfCityCountry = new LinkedHashMap<>();
        this.numberOfTagsForCity = new LinkedHashMap<>();
        try (var readFile = new BufferedReader(citiesReader)) {
            String line;
            while ((line = readFile.readLine()) != null) {
                String[] lines = line.split(",");
                listOfCityCountry.put(lines[0], lines[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Processes an input stream of a text file, tags any cities and outputs result
     * to a text output stream.
     *
     * @param text   a java.io.Reader input stream containing text to be processed
     * @param output a java.io.Writer output stream containing the result of tagging
     */
    public void tagCities(Reader text, Writer output) {
        numberOfTagsForCity.clear();
        try (var readText = new BufferedReader(text); var writeText = new BufferedWriter(output)) {
            String line;
            while ((line = readText.readLine()) != null) {
                for (String city : this.listOfCityCountry.keySet()) {
                    line = this.countTagForCity(line, city);
                }
                writeText.write(line);
                writeText.newLine();
            }
            writeText.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Returns a collection the top @n most tagged cities' unique names
     * from the last tagCities() invocation. Note that if a particular city has been tagged
     * more than once in the text, just one occurrence of its name should appear in the result.
     * If @n exceeds the total number of cities tagged, return as many as available
     * If tagCities() has not been invoked at all, return an empty collection.
     *
     * @param n the maximum number of top tagged cities to return
     * @return a collection the top @n most tagged cities' unique names
     * from the last tagCities() invocation.
     */
    public Collection<String> getNMostTaggedCities(int n) {
        List<String> newCollection = new ArrayList<>();
        if (this.numberOfTagsForCity.isEmpty()) {
            return newCollection;
        }
        if (numberOfTagsForCity.size() < n) {
            newCollection.addAll(numberOfTagsForCity.keySet());
            return newCollection;
        } else {
            newCollection.addAll(this.sortByValue(numberOfTagsForCity).keySet());
            return newCollection.subList(0, n);
        }
    }

    /**
     * Returns a collection of all tagged cities' unique names
     * from the last tagCities() invocation. Note that if a particular city has been tagged
     * more than once in the text, just one occurrence of its name should appear in the result.
     * If tagCities() has not been invoked at all, return an empty collection.
     *
     * @return a collection of all tagged cities' unique names
     * from the last tagCities() invocation.
     */
    public Collection<String> getAllTaggedCities() {
        List<String> newCollection = new ArrayList<>();
        if (!numberOfTagsForCity.isEmpty()) {
            newCollection.addAll(numberOfTagsForCity.keySet());
        }
        return newCollection;
    }

    /**
     * Returns the total number of tagged cities in the input text
     * from the last tagCities() invocation
     * In case a particular city has been tagged in several occurrences, all must be counted.
     * If tagCities() has not been invoked at all, return 0.
     *
     * @return the total number of tagged cities in the input text
     */
    public long getAllTagsCount() {
        int count = 0;
        for (Integer integer : numberOfTagsForCity.values()) {
            count += integer;
        }
        return count;
    }

    public Map<String, String> getListOfCityCountry() {
        return this.listOfCityCountry;
    }

    public Map<String, Integer> getNumberOfTagsForCity() {
        return numberOfTagsForCity;
    }

}
