import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Car porsche=new Car();
        porsche.setColour("Blue");
        porsche.setDoors(4);
        porsche.setEngine("Motor");
        porsche.setModel("Porsche");
        porsche.setWheels(4);
        porsche.printCar();
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the model's name: ");
        String model=scanner.nextLine();
        System.out.print("Enter the engine's name: ");
        String engine=scanner.nextLine();
        System.out.print("Enter the colour's name: ");
        String colour=scanner.nextLine();
        System.out.print("Enter the number of doors: ");
        boolean isDoorsANumber=scanner.hasNextInt();
        if(!isDoorsANumber)System.out.println("Error, you should input a number!");
        else
            {
                int doors=scanner.nextInt();
                System.out.print("Enter the number of wheels: ");
                boolean isWheelsANumber=scanner.hasNextInt();
                if(!isWheelsANumber)System.out.println("Error, you should input a number!");
                else
                {
                    int wheels = scanner.nextInt();
                    scanner.nextLine();
                    Car holden=new Car(model, engine, colour, doors, wheels);
                    holden.printCar();
                }
            }
        Car unknown=new Car();
        unknown.printCar();

        scanner.close();
    }
}
