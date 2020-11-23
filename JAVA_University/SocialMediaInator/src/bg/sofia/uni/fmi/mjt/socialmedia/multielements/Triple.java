package bg.sofia.uni.fmi.mjt.socialmedia.multielements;

public class Triple<K, L, T> extends Pair<K, L> {

    private T third;

    public Triple(K first, L second, T third) {
        super(first, second);
        this.third = third;
    }

    public T getThird() {
        return third;
    }

    public void setThird(T third) {
        this.third = third;
    }

    @Override
    public K getFirst() {
        return super.getFirst();
    }

    @Override
    public void setFirst(K first) {
        super.setFirst(first);
    }

    @Override
    public L getSecond() {
        return super.getSecond();
    }

    @Override
    public void setSecond(L second) {
        super.setSecond(second);
    }
}
