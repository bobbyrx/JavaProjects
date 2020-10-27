import java.util.Scanner;
public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static Bank DSK=new Bank();
    public static void main(String[] args) {
        boolean ifStop=false;
        while(!ifStop){
            System.out.print("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    addBranch();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    addTransaction();
                    break;
                case 4:
                    printBranch();
                    break;
                case 5:
                    printBank();
                    break;
                case 6:
                    printMenu();
                    break;
                case 7:
                    if(askToQuit().equals("yes"))ifStop=true;
                    break;
                default:
                    break;
            }
        }
    }

    public static void addBranch(){
        Branch newBranch=new Branch();
        DSK.addBranch(newBranch);
    }

    public static void addCustomer(){
        System.out.print("Enter the name of customer: ");
        String name=scanner.nextLine();
        System.out.print("Enter the initial transaction: ");
        double firstTransaction=scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter the index of which branch you want to add the customer: ");
        int indexOfBranch=scanner.nextInt();
        scanner.nextLine();
        if(indexOfBranch>=DSK.getArraySize())System.out.println("No such branch found!");
        else DSK.addCustomerToBranch(name,firstTransaction,DSK.getBranch(indexOfBranch));
    }

    public static void addTransaction(){
        System.out.print("Enter the name of customer: ");
        String name=scanner.nextLine();
        System.out.print("Enter the new transaction: ");
        double newTransaction=scanner.nextDouble();
        scanner.nextLine();
        DSK.addTransactionToCustomerFromBranch(name,newTransaction);
    }

    public static void printBranch(){
        System.out.print("Enter the index of which branch you want to print: ");
        int indexOfBranch=scanner.nextInt();
        scanner.nextLine();
        if(indexOfBranch>=DSK.getArraySize())System.out.println("Branch not found!");
        else DSK.printBranch(DSK.getBranch(indexOfBranch));
    }

    public static void printBank(){
        DSK.print();
    }

    public static void printMenu(){
        System.out.println("1 -> Add branch");
        System.out.println("2 -> Add customer");
        System.out.println("3 -> Add transaction to a existing customer");
        System.out.println("4 -> Print a certain branch");
        System.out.println("5 -> Print the bank");
        System.out.println("6 -> Print menu");
        System.out.println("7 -> Quit");
    }

    private static String askToQuit(){
        System.out.print("Do you want to quit(yes/no)? ");
        String sureIfWantToQuit=scanner.nextLine();
        return sureIfWantToQuit.toLowerCase();
    }

}
