import java.util.ArrayList;

public class GroceryList {
    private ArrayList<String> groceryList=new ArrayList<String>();

    public void addGroceryItem(String name){
        groceryList.add(name);
    }

    public void printGroceryList(){
        for(int i=0;i<groceryList.size();i++){
            System.out.println((i+1)+". "+groceryList.get(i));
        }
    }

    public void removeGroceryItem(int position){
        groceryList.remove(position);
    }

    public void modifyGroceryItem(int position, String newItem){
        groceryList.set(position,newItem);
        System.out.println("Item changed");
    }

    public int findItems(String searchItem){
     return groceryList.indexOf((searchItem));
    }

    public void modifyGroceryItem(String newItem){
        int position=findItems(newItem);
        if(position>=0){
            modifyGroceryItem(position,newItem);
        }
    }

    public void removeGroceryItem(String item){
        int position=findItems(item);
        if(position>=0){
            removeGroceryItem(position);
        }
    }
}
