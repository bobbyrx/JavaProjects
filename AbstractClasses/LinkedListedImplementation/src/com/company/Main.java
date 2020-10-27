package com.company;

public class Main {

    public static void main(String[] args) {
        MyLinkedList list=new MyLinkedList();
        list.addItem(new Node("Home"));
        list.addItem(new Node("door"));
        list.addItem(new Node("floor"));
        list.traverse();
        list.addItem(new Node("already"));
        list.addItem(new Node("Aaaa"));
        list.traverse();
        list.removeItem(new Node("Aaaa"));
        list.removeItem(new Node("already"));
        list.removeItem(new Node("floor"));
        list.traverse();
    }
}
