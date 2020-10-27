package com.company;

public class MyLinkedList implements NodeList {
    Node root=null;
    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(Node newNode) {
        if(this.root==null){
            root=newNode;
            return true;
        }
        else{
            Node currentList=this.root;
            while(currentList!=null){
                int compare=currentList.compareTo(newNode);
                if(compare<0){
                    if(currentList.next()!=null){
                        currentList=(Node)currentList.next();
                    }
                    else{
                        newNode.setPreviousItem(currentList).setNextItem(newNode);
                        return true;
                    }
                }
                else if(compare>0){
                    if(currentList.previous()!=null){
                        currentList.previous().setNextItem(newNode).setPreviousItem(currentList.previous());
                        currentList.setPreviousItem(newNode).setNextItem(currentList);
                    }
                    else{
                        this.root.setPreviousItem(newNode).setNextItem(this.root);
                        this.root=newNode;
                    }
                    return true;
                }
                else{
                    System.out.println("This node is already in this Linked List");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(Node deleteNode) {
        if(deleteNode!=null) {
            Node currentList = this.root;
            while (currentList!=null){
                int compare=currentList.compareTo(deleteNode);
                if(compare==0){
                    if(currentList.previous()==null){
                        this.root=(Node)this.root.next();
                        return true;
                    }
                    else if(currentList.next()==null){
                        currentList.previous().setNextItem(null);
                        return true;
                    }
                    else{
                        currentList.previous().setNextItem(currentList.next()).setPreviousItem(currentList.previous());
                        return true;
                    }
                }
                else {
                    currentList=(Node)currentList.next();
                }
            }
        }
        else System.out.println("This not can't be deleted!\nIt's value is null!");
        return false;
    }

    @Override
    public void traverse() {
        if(this.root==null) System.out.println("The list is empty!");
        else {
            Node currentList=this.root;
            System.out.println("Printing List:");
            while(currentList!=null){
                System.out.println(currentList.getValue());
                currentList=(Node)currentList.next();
            }
        }
    }
}
