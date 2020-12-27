package bg.sofia.uni.fmi.mjt.spellchecker;

public record Metadata(int characters, int words, int mistakes) {
    @Override
    public String toString() {
        return "= = = Metadata = = =\n"
                + this.characters() + " characters, " + this.words() + " words, "
                + this.mistakes() + " spelling issue(s) found";
    }
}