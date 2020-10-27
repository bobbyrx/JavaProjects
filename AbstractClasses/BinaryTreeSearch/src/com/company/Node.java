package com.company;

public class Node extends ListItem{
    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem setRightItem(ListItem newListItem) {
        this.rightItem=newListItem;
        return this.rightItem;
    }

    @Override
    ListItem left() {
        return this.leftItem;
    }

    @Override
    ListItem setLeftItem(ListItem newListItem) {
        this.leftItem=newListItem;
        return this.leftItem;
    }

    @Override
    ListItem right() {
        return this.rightItem;
    }

    @Override
    int compareTo(ListItem newListItem) {
        if(newListItem!=null) {
            return ((String) this.getValue()).compareTo((String) newListItem.getValue());
        }
        else return -1;
    }
}
