import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> arrayDouble;

    public Customer(String name, ArrayList<Double> arrayDouble) {
        this.name = name;
        this.arrayDouble = arrayDouble;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getArrayDouble() {
        return arrayDouble;
    }

    public void addTransactions(double transaction){
        if(transaction>0.0)arrayDouble.add(transaction);
        else System.out.println("Transactions can't be less than 0.0");
    }

    public void print(){
        System.out.println("Customer's name: "+this.getName());
        for(int i=0;i<arrayDouble.size();i++){
            System.out.println("Transaction ["+(i+1)+"]-> "+arrayDouble.get(i));
        }
        System.out.println();
    }

}
