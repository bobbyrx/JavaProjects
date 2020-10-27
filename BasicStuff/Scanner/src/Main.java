import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter your year of birth: ");
        boolean isNum=scanner.hasNextInt();
        while(!isNum)
        {
            scanner.nextLine();
            System.out.println("Error, you should input a integer!");
            System.out.print("Enter your year of birth: ");
            isNum=scanner.hasNextInt();
        }
            int yearOfBirth = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            int age = 2020 - yearOfBirth;
            if(age<0||age>100)
            {
                System.out.println("Error, year of birth is wrongly input.");
            }
            else System.out.println("Your name is " + name + " and you are " + age + " years old.");

        scanner.close();
    }
}
