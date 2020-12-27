package bg.sofia.uni.fmi.mjt.spellchecker;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class NaiveSpellChekerCompilationTest {

    private final String text = "Unrivaled remains know history a major street prettiest."
            + " Cities Europe nowhere\n"
            + "Bulgaria, after 21cities#!@#, Bulgrria!@$!.\n"
            + "Architecture popular a spots, Bulgaria? Art "
            + "a popular chery major and history street #!@desperatelly by.\n";

    private final String dictionary = "beauty\n"
            + " fair^@$!\n"
            + "!@$nowhere  \n"
            + "cherry\n"
            + "blossoms\n"
            + "fear$!@\n"
            + "popular\n"
            + "spots\n"
            + "cities\n"
            + " #!@Europe\n"
            + "know#!@\n"
            + " #!@Bulgaria#!\n"
            + "#!!@#major!#@#!\n"
            + "desperately\n"
            + "prettiest\n"
            + "remains\n"
            + "unrivaled\n"
            + "!#!@#history#!@#@!\n"
            + "art\n"
            + "#!@#!@architecture   \n"
            + "#!@#!@#street     \n";

    private final String dictionaryCorrect = "[beauty, "
            + "fair, "
            + "nowhere, "
            + "cherry, "
            + "blossoms, "
            + "fear, "
            + "popular, "
            + "spots, "
            + "cities, "
            + "europe, "
            + "know, "
            + "bulgaria, "
            + "major, "
            + "desperately, "
            + "prettiest, "
            + "remains, "
            + "unrivaled, "
            + "history, "
            + "art, "
            + "architecture, "
            + "street]";

    private final String stopWords = "    !@#$a\n"
            + "#!@about\n"
            + "above!@#\n"
            + "after\n"
            + "all\n"
            + "am\n"
            + "an#!@\n"
            + "#!@and#!@\n"
            + "any\n"
            + "are\n"
            + "aren't\n"
            + "as#!@\n"
            + "at\n"
            + "be\n"
            + "because\n"
            + "been#!@\n"
            + "being\n"
            + "below#!@\n"
            + "between  \n"
            + "both#!@\n"
            + "but\n"
            + "#!@by\n"
            + "!#@can't\n"
            + "cannot\n"
            + "could\n"
            + "#!@did\n"
            + "didn't\n"
            + "do\n"
            + "does\n"
            + "doesn't\n"
            + "doing\n"
            + "down\n"
            + "during\n"
            + "each\n"
            + "few\n"
            + "!@#!for  \n"
            + "from\n"
            + "had\n"
            + "hadn't\n"
            + "has\n"
            + "hasn't\n"
            + "have\n"
            + "haven't\n"
            + "having\n";

    private final String stopWordsCorrect = "[a, "
            + "about, "
            + "above, "
            + "after, "
            + "all, "
            + "am, "
            + "an, "
            + "and, "
            + "any, "
            + "are, "
            + "aren't, "
            + "as, "
            + "at, "
            + "be, "
            + "because, "
            + "been, "
            + "being, "
            + "below, "
            + "between, "
            + "both, "
            + "but, "
            + "by, "
            + "can't, "
            + "cannot, "
            + "could, "
            + "did, "
            + "didn't, "
            + "do, "
            + "does, "
            + "doesn't, "
            + "doing, "
            + "down, "
            + "during, "
            + "each, "
            + "few, "
            + "for, "
            + "from, "
            + "had, "
            + "hadn't, "
            + "has, "
            + "hasn't, "
            + "have, "
            + "haven't, "
            + "having]";

    private final String outputCorrect = "Unrivaled remains know history a major street prettiest."
            + " Cities Europe nowhere\n"
            + "Bulgaria, after 21cities#!@#, Bulgrria!@$!.\n"
            + "Architecture popular a spots, Bulgaria? Art a popular chery major and "
            + "history street #!@desperatelly by.\n"
            + "= = = Metadata = = =\n"
            + "198 characters, 24 words, 4 spelling issue(s) found\n"
            + "= = = Findings = = =\n"
            + "Line #2, {21cities} - Possible suggestions are {cities, prettiest}\n"
            + "Line #2, {Bulgrria} - Possible suggestions are {bulgaria, cherry}\n"
            + "Line #3, {chery} - Possible suggestions are {cherry, nowhere}\n"
            + "Line #3, {desperatelly} - Possible suggestions are {desperately, spots}";

    private final String metadataCorrect = "= = = Metadata = = =\n"
            + "198 characters, 24 words, 4 spelling issue(s) found";

    private final Reader dictionaryReader = new StringReader(dictionary);
    private final Reader soptwordsReader = new StringReader(stopWords);

    @Test
    public void testIfCorrectingDictionaryAndStopWords() throws IOException {

        NaiveSpellChecker spellChecker = new NaiveSpellChecker(dictionaryReader, soptwordsReader);

        assertEquals("Doesn't correct the dictionary!",
                spellChecker.getDictionary().toString(), dictionaryCorrect);
        assertEquals("Doesn't correct the stopWords!",
                spellChecker.getStopWords().toString(), stopWordsCorrect);
    }

    @Test
    public void testMetadata() throws IOException {

        SpellChecker spellChecker = new NaiveSpellChecker(dictionaryReader, soptwordsReader);

        assertEquals("There is a problem with metadata",
                spellChecker.metadata(new StringReader(text)).toString(), metadataCorrect);
    }

    @Test
    public void testAnalyze() throws IOException {

        SpellChecker spellChecker = new NaiveSpellChecker(dictionaryReader, soptwordsReader);

        StringWriter output = new StringWriter(text.length());

        spellChecker.analyze(new StringReader(text), output, 2);

        assertEquals("Does't correct the text!", output.toString(), outputCorrect);
    }

    @Test
    public void testPair() {

        Pair<Integer, Double> newPair = new Pair<>(4, 5.5);

        newPair.setFirst(6);
        assertEquals("Doesn't set the first value",
                newPair.getFirst().toString(), Integer.toString(6));

        newPair.setSecond(9.8);
        assertEquals("Doesn't set the second value",
                newPair.getSecond().toString(), Double.toString(9.8));

        assertEquals("Doesn't print correctly the values",
                newPair.toString(), "Pair{first=6, second=9.8}");
    }
}