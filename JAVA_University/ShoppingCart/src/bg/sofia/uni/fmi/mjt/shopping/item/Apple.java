package bg.sofia.uni.fmi.mjt.shopping.item;

import java.util.Objects;

public class Apple implements Item, Comparable<Item> {

    private String id;

    public Apple(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Item item) {
        return this.id.compareTo(item.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Apple apple = (Apple) o;
        return Objects.equals(id, apple.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}