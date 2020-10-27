package com.company;

public class Node extends ListItem{

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem setNextItem(ListItem newListItem) {
      return this.nextItem=newListItem;
    }

    @Override
    ListItem next() {
        return this.nextItem;
    }

    @Override
    ListItem setPreviousItem(ListItem newListItem) {
        return this.previousItem=newListItem;
    }

    @Override
    ListItem previous() {
        return this.previousItem;
    }

    @Override
    int compareTo(ListItem newListItem) {
       if(newListItem!=null){
           return ((String)super.getValue()).compareTo((String)newListItem.getValue());
       }
       else return -1;
    }
}
