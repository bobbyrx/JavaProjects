package com.company;

import javafx.scene.control.skin.SliderSkin;

public class BinaryTree implements NodeList {
    Node root=null;
    @Override
    public Node getRoot() {
        return this.root;
    }

    public void addNewItem(Object name){
        this.root=addItem(this.root,name);
    }

    @Override
    public Node addItem(Node root, Object name) {
        if(root==null){
            root=new Node(name);
            return root;
        }
        else{
            int compare=root.compareTo(new Node(name));
            if(compare>0){
                root.setLeftItem(addItem((Node)root.left(),name));
            }
            else if(compare<0){
                root.setRightItem(addItem((Node)root.right(),name));
            }
        }
        return root;
    }

    private Object minValue(Node root){
        Object minV=root.getValue();
        while(root.left()!=null){
            minV=root.left().getValue();
            root=(Node)root.left();
        }
        return minV;
    }

    public void deleteItem(Object name){
        this.root=removeItem(this.root,new Node(name));
    }

    @Override
    public Node removeItem(Node root,Node deleteNode) {
        int compare=root.compareTo(deleteNode);
        if(root==null)return root;
        else if(compare>0){
            root.setLeftItem(removeItem((Node)root.left(),deleteNode));
        }
        else if(compare<0){
            root.setRightItem(removeItem((Node)root.right(),deleteNode));
        }
        else{
            if(root.right()==null)return (Node)root.left();
            else if(root.left()==null)return (Node)root.right();

            root.setValue(minValue((Node)root.right()));
            root.setRightItem(removeItem((Node)root.right(),root));
        }
        return root;
    }

    @Override
    public void traverse(String path,Node root) {
        if(root!=null){
            traverse("Left",(Node)root.left());
            System.out.println(path+": "+root.getValue());
            traverse("Right",(Node)root.right());
        }
    }
}
