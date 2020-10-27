import java.util.Scanner;

public class Main {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        int[] array=getInteger();
        System.out.println();
        printArray(array);
        System.out.println();
        System.out.println("The minimal number is: "+findMin(array));
    }
    private static int[] getInteger(){
        System.out.print("Enter how much numbers you want to input: ");
        int number=scanner.nextInt();
        int[] newArray=new int[number];
        for(int i=0;i<newArray.length;i++){
            System.out.print("Enter your number: ");
            int num=scanner.nextInt();
            newArray[i]=num;
        }
        return newArray;
    }
    private static void printArray(int[] array){
        for(int i=0;i<array.length;i++) System.out.println("array["+i+"] -> "+array[i]);
    }
    private static int findMin(int[] array){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<array.length;i++){
            if(min>array[i])min=array[i];
        }
        return min;
    }

}
