import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("Print your number: ");
        int num=scan.nextInt();
        double number=SpeedConverter.toMilesPerHour(num);
        System.out.println("number = "+number);
        SpeedConverter.printConversion(number);
    }
}
