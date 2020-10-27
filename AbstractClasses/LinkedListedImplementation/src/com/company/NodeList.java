package com.company;

public interface NodeList {
    Node getRoot();
    boolean addItem(Node newNode);
    boolean removeItem(Node deleteNode);
    void traverse();
}
