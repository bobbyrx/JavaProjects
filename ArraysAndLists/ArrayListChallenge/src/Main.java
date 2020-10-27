import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        MobilePhone mobilePhone=new MobilePhone();
        boolean stop=false;
        while(!stop) {
            System.out.print("Enter what you want to do: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1:
                    System.out.println("Quiting!");
                    stop = true;
                    break;
                case 2:
                    mobilePhone.printContacts();
                    break;
                case 3:
                    System.out.println("Add new item:");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    mobilePhone.addContact(new Contacts(name, phoneNumber));
                    break;
                case 4:
                    System.out.println("Update contact:");
                    System.out.print("Enter which contact you want to update: ");
                    int contact = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    mobilePhone.updateContacts(contact, new Contacts(newName, newPhoneNumber));
                    break;
                case 5:
                    System.out.println("Remove contact:");
                    System.out.print("Enter which number you want to remove: ");
                    int removeNumber = scanner.nextInt();
                    scanner.nextLine();
                    mobilePhone.removeContacts(removeNumber);
                    break;
                case 6:
                    System.out.println("Find element:");
                    System.out.print("Enter the name of what contact you are searching for: ");
                    String findName = scanner.nextLine();
                    System.out.print("Enter the phone number of what contact you are searching for: ");
                    String findPhoneNumber = scanner.nextLine();
                    if (mobilePhone.ifExist(new Contacts(findName, findPhoneNumber))) {
                        System.out.println("Contact found. And the index is " +
                                mobilePhone.findElement(new Contacts(findName, findPhoneNumber)));
                    } else System.out.println("Contact not found!");
                    break;
                case 7:
                    System.out.println("Printing contact information:");
                    System.out.print("Enter what contact's information you want to have: ");
                    int getNumber=scanner.nextInt();
                    scanner.nextLine();
                    if(mobilePhone.getContacts(getNumber)!=null){
                        System.out.println("Name: "+mobilePhone.getContacts(getNumber).getName());
                        System.out.println("Phone number: "+mobilePhone.getContacts(getNumber).getNumberPhone());
                    }
                    else System.out.println("Sorry, no such contact!");
                    break;
                default:
                    break;
            }
        }

    }
}
