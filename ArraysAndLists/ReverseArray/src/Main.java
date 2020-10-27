import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        int[] array=setArray(3);
        getArray(array);
        System.out.println();
        reverseArray(array);
        getArray(array);
        array= Arrays.copyOf(array,array.length+10);
        getArray(array);
    }
    private static int[] setArray(int number){
        int[] newArray=new int[number];
        for(int i=0;i<newArray.length;i++){
            System.out.print("Enter the "+i+" number: ");
            int num=scanner.nextInt();
            newArray[i]=num;
        }
        return newArray;
    }
    private static void getArray(int[] array){
        System.out.println("Elements: "+ Arrays.toString(array));
    }
    private static void reverseArray(int[] array){
        int start=0;
        int end=array.length-1;
        while(start<end){
            int num=array[end];
            array[end]=array[start];
            array[start]=num;
            start++;
            end--;
        }
    }
}
