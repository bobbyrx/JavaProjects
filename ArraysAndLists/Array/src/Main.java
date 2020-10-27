import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        int[] array=getArray(5);
        printArray(array);
        sortArray(array);
        System.out.println();
        printArray(array);
       int[]array2=sortArray2(array);
        System.out.println();
        printArray(array2);
        array2[0]=0;
        sortArray(array2);
        System.out.println();
        printArray(array2);
        System.out.println();
        printArray(array);

    }

    public static int[] getArray(int number){
        if(number<=0){
            int[] array=new int[0];
            return array;
        }
        int[] array=new int[number];
        for(int i=0;i<number;i++){
            System.out.print("Enter your number: ");
            int scan=scanner.nextInt();
            array[i]=scan;
        }
        return array;
    }

    public static void printArray(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.println("array["+i+"] -> "+array[i]);
        }
    }

    public static void sortArray(int[] array){

        for(int i=0;i<array.length;i++)
        {
            for(int j=i+1;j<array.length;j++)
            {
                if(array[i]<array[j]) {
                        int num=array[i];
                        array[i]=array[j];
                        array[j]=num;
                }
                else continue;
            }
        }
    }

    public static int[] sortArray2(int[] array){

        for(int i=0;i<array.length;i++)
        {
            int min=array[i];
            int minInt=i;
            for(int j=i+1;j<array.length;j++)
            {
                if(array[j]<min){
                    min=array[j];
                    minInt=j;
                }
                else continue;
            }
            if(min!=array[i]){
                array[minInt]=array[i];
                array[i]=min;
            }
        }
        int[] array2= Arrays.copyOf(array,array.length);
        return array2;
    }
}
