package bg.sofia.uni.fmi.mjt.spellchecker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NaiveSpellChecker implements SpellChecker {

    private final List<String> dictionary;
    private final List<String> stopWords;

    private Map<Pair<String, Integer>, List<String>> findingsList;

    public List<String> getDictionary() {
        return dictionary;
    }

    public List<String> getStopWords() {
        return stopWords;
    }

    public NaiveSpellChecker(Reader dictionaryReader, Reader stopwordsReader) throws IOException {

        findingsList = new LinkedHashMap<>();
        dictionary = new ArrayList<>();
        stopWords = new ArrayList<>();

        try (var readDictionary = new BufferedReader(dictionaryReader);
             var readStopWords = new BufferedReader(stopwordsReader)) {

            String lineFromDictionary;

            while ((lineFromDictionary = readDictionary.readLine()) != null) {

                lineFromDictionary = lineFromDictionary
                        .replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "")
                        .toLowerCase();

                if (lineFromDictionary.length() > 1) {
                    dictionary.add(lineFromDictionary);
                }
            }

            String lineFromStopWords;

            while ((lineFromStopWords = readStopWords.readLine()) != null) {

                lineFromStopWords = lineFromStopWords
                        .replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "")
                        .toLowerCase();

                stopWords.add(lineFromStopWords);
            }
        } catch (IOException e) {
            throw new IOException("There is a problem with reading from files");
        }
    }

    @Override
    public void analyze(Reader textReader, Writer output, int suggestionsCount) {

        findingsList.clear();

        if (suggestionsCount <= 0) {
            throw new RuntimeException("SuggestionsCount value must be positive!");
        }

        try (var reader = new BufferedReader(textReader);
             var writer = new BufferedWriter(output)) {

            String line;
            int lineNum = 1;

            while ((line = reader.readLine()) != null) {

                writer.write(line + "\n");
                scanLine(line, lineNum++, suggestionsCount);

            }

            textReader.reset();
            Metadata newMetadata = metadata(textReader);

            writer.write(newMetadata.toString());
            writer.write(writeFindings());

            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with analyzing from file");
        }
    }

    @Override
    public Metadata metadata(Reader textReader) {

        int characters = 0;
        int wholeWords = 0;
        int mistakes = 0;

        try (var reader = new BufferedReader(textReader)) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.replaceAll("\\s+", " ").trim();

                List<String> words = Arrays.stream(line.split(" "))
                        .collect(Collectors.toList());

                for (String word : words) {

                    characters += word.length();

                    word = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "")
                            .toLowerCase();

                    if (!stopWords.contains(word) && !word.isEmpty()) {
                        wholeWords++;
                        if (!dictionary.contains(word)) {
                            mistakes++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("There is a problem with metadata function");
        }
        return new Metadata(characters, wholeWords, mistakes);
    }

    public void scanLine(String line, int lineNumber, int suggestionsCount) {

        line = line.replaceAll("\\s+", " ").trim();

        List<String> words = Arrays.stream(line.split(" "))
                .collect(Collectors.toList());

        for (String word : words) {

            word = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

            if (!stopWords.contains(word.toLowerCase())
                    && !word.isEmpty() && !dictionary.contains(word.toLowerCase())) {

                findingsList.put(new Pair<>(word, lineNumber),
                        findClosestWords(word.toLowerCase(), suggestionsCount));
            }
        }
    }

    @Override
    public List<String> findClosestWords(String word, int n) {

        Map<String, Integer> word1 = new LinkedHashMap<>(gramOfWord(word));
        Set<Pair<String, Double>> suggestedWords = new HashSet<>();

        for (String newWord : dictionary) {

            Map<String, Integer> word2 = new LinkedHashMap<>(gramOfWord(newWord));

            double compatibility;
            double multiplyVectors = word1.size() * word2.size();
            double dotProduct = 0.0;

            for (String newGram : word1.keySet()) {
                if (word2.containsKey(newGram)) {
                    dotProduct += word1.get(newGram) * word2.get(newGram);
                }
            }
            compatibility = dotProduct / multiplyVectors;
            suggestedWords.add(new Pair<>(newWord, compatibility));
        }

        return suggestedWords.stream()
                .sorted((n1, n2) -> n2.getSecond().compareTo(n1.getSecond()))
                .limit(n).map(Pair::getFirst).collect(Collectors.toList());
    }

    public Map<String, Integer> gramOfWord(String word) {

        Map<String, Integer> wordGram = new LinkedHashMap<>();

        for (int i = 0; i < word.length(); i++) {

            if (i + 1 < word.length()) {

                String gram = "" + word.charAt(i) + word.charAt(i + 1);

                if (!wordGram.containsKey(gram)) {
                    wordGram.put(gram, 1);
                } else {
                    wordGram.put(gram, wordGram.get(gram) + 1);
                }

            } else {
                break;
            }
        }
        return wordGram;
    }

    public String writeFindings() {

        String textOutput = "\n= = = Findings = = =";
        for (Pair<String, Integer> newPair : findingsList.keySet()) {

            textOutput += "\nLine #" + newPair.getSecond() + ", {" + newPair.getFirst() + "} - "
                    + "Possible suggestions are {";

            for (int i = 0; i < findingsList.get(newPair).size(); i++) {

                if (i + 1 < findingsList.get(newPair).size()) {

                    textOutput += findingsList.get(newPair).get(i) + ", ";

                } else {
                    textOutput += findingsList.get(newPair).get(i);
                }
            }
            textOutput += "}";
        }
        return textOutput;
    }
}
