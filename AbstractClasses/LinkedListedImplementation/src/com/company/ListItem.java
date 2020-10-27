package com.company;

public abstract class ListItem {
    protected ListItem nextItem=null;
    protected ListItem previousItem=null;

    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem setNextItem(ListItem newListItem);
    abstract ListItem next();
    abstract ListItem setPreviousItem(ListItem newListItem);
    abstract ListItem previous();

    abstract int compareTo(ListItem newListItem);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
