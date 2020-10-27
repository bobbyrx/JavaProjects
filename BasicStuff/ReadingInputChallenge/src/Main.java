import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num;
        int sum=0;
        for(int i=0;i<10;i++)
        {
            System.out.print("Input the "+(i+1)+" number: ");
            boolean isNum=scanner.hasNextInt();
            while(!isNum)
            {
                System.out.println("Error, you should input a number!");
                System.out.print("Input the "+(i+1)+" number: ");
                scanner.nextLine();
                isNum=scanner.hasNextInt();
            }
            num=scanner.nextInt();
            scanner.nextLine();
            sum+=num;
        }
        System.out.println("The sum of the numbers is: "+sum);
        scanner.close();
    }
}
