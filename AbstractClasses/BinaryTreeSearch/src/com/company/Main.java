package com.company;

public class Main {

    public static void main(String[] args) {
	    BinaryTree binaryTree=new BinaryTree();
	    binaryTree.addNewItem("D");
        binaryTree.addNewItem("A");
        binaryTree.addNewItem("B");
        binaryTree.addNewItem("E");
        binaryTree.addNewItem("C");
        binaryTree.addNewItem("D");
        binaryTree.deleteItem("D");
        binaryTree.traverse("Root",binaryTree.getRoot());
    }
}
