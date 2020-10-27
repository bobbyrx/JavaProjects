import java.util.ArrayList;
import java.util.Scanner;

public class Branch {
    private ArrayList<Customer> arrayCustomer=new ArrayList<Customer>();
    private static Scanner scanner=new Scanner(System.in);

    public  boolean ifExist(String name){
        for(int i=0;i<arrayCustomer.size();i++){
            if(name.equals(arrayCustomer.get(i).getName()))return true;
        }
        return false;
    }

    private int ifExistReturnIndex(String name) {
        for (int i = 0; i < arrayCustomer.size(); i++) {
            if (name.equals(arrayCustomer.get(i).getName())) return i;
        }
        return -1;
    }

    public void addCustomer(Customer customer){
        arrayCustomer.add(customer);
    }

    public void addCustomer(String name,double firstTransaction){
        if(this.ifExist(name)) System.out.println("Already exist this customer!");
        else {
            ArrayList<Double> arrayDouble=new ArrayList<Double>();
            arrayDouble.add(firstTransaction);
            Customer customer=new Customer(name,arrayDouble);
            arrayCustomer.add(customer);
        }
    }

    public void addTransaction(String name, double newTransaction){
            int index=this.ifExistReturnIndex(name);
            arrayCustomer.get(index).addTransactions(newTransaction);
    }

    public Customer getCustomer(String name){
        if(ifExistReturnIndex(name)!=-1)return arrayCustomer.get(ifExistReturnIndex(name));
        else return null;
    }

    public void print(){
        System.out.print("Do you want to print the transactions(yes/no)? ");
        String askQuestion=scanner.nextLine();
        if(askQuestion.toLowerCase().equals("yes")){
        System.out.println("Branch "+this.getClass().getSimpleName()+":");
        System.out.println();
        for(int i=0;i<arrayCustomer.size();i++){
            arrayCustomer.get(i).print();
        }
        }
        else {
            System.out.println("Branch "+this.getClass().getSimpleName()+":");
            System.out.println();
            for(int i=0;i<arrayCustomer.size();i++){
                System.out.println("Customer's name: "+arrayCustomer.get(i).getName());
            }
        }
    }
}
