import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        while(true)
        {
            System.out.print("Enter number: ");
            boolean isNum=scanner.hasNextInt();
            if(!isNum)break;
            else
            {
                int number=scanner.nextInt();
                if(min>number)min=number;
                if(max<number)max=number;
            }
            scanner.nextLine();
        }
        if(max==Integer.MIN_VALUE&&min==Integer.MAX_VALUE)System.out.println("There is no max and min number!");
        else System.out.println("Max: "+max+" Min: "+min);
        scanner.close();
    }
}
