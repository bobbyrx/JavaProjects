package com.company;

import javafx.scene.control.skin.SliderSkin;

public interface NodeList {
    Node getRoot();
    Node addItem(Node root, Object name);
    Node removeItem(Node root,Node deleteNode);
    void traverse(String path,Node root);
}
