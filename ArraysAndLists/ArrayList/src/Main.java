import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<String> array=new ArrayList<String>();
        array.add("Hello");
        array.add("Bobby");
        ArrayList<String> array2=new ArrayList<String>();
        array2.add("Falco");
        System.out.println("Arr[0]= "+array2.get(0)+" Arr[1]= "+array2.get(1)+" Arr[2]= "+array2.get(2));
        System.out.println("Arr[0]= "+array.get(0)+" Arr[1]= "+array.get(1));
    }
}
