package bg.sofia.uni.fmi.mjt.socialmedia.multielements;

public class Pair<K, L> {

    private K first;
    private L second;

    public Pair(K first, L second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public L getSecond() {
        return second;
    }

    public void setSecond(L second) {
        this.second = second;
    }
}
