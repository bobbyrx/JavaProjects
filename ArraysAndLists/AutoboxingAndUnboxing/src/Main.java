import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        Integer integer= 54;
        //This is for when you want
        //to make an ArrayList with primitive type value
        //because Integer, Double and others are classes
        //and ArrayLists can't be of primitive type value!!!
        Integer integer1=64;//You can ride it also like this!
        ArrayList<Integer> arrayInt=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            arrayInt.add(i);
        }
        for(int i=0;i<10;i++){
            System.out.println("ArrayInt["+i+"]-> "+arrayInt.get(i));
        }
    }
}
