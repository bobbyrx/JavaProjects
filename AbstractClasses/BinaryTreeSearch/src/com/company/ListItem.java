package com.company;

public abstract class ListItem {
    protected ListItem rightItem=null;
    protected ListItem leftItem=null;

    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem setRightItem(ListItem newListItem);
    abstract ListItem left();
    abstract ListItem setLeftItem(ListItem newListItem);
    abstract ListItem right();

    abstract int compareTo(ListItem newListItem);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
