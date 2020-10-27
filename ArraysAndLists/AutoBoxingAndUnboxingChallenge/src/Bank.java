import java.util.ArrayList;

public class Bank {
    private ArrayList<Branch> arrayBranch=new ArrayList<Branch>();

    public void addBranch(Branch newBranch){
        arrayBranch.add(newBranch);
        System.out.println("Successfully added new branch to "+this.getClass().getSimpleName()+" bank");
    }

    public boolean ifCustomerExistInBank(String name){
        for(int i=0;i<arrayBranch.size();i++){
            if(arrayBranch.get(i).ifExist(name))return true;
        }
        return false;
    }

    public void addCustomerToBranch(String name,double firstTransaction,Branch branch){
            if(this.ifCustomerExistInBank(name)){
                System.out.println("This customer already exists!");
            }else {
                arrayBranch.get(arrayBranch.indexOf(branch)).addCustomer(name, firstTransaction);
                System.out.println("Successfully added a new customer to " + branch.getClass().getSimpleName() + " branch");
            }
    }

    public void addTransactionToCustomerFromBranch(String name,double newTransaction){
        if(this.ifCustomerExistInBank(name)){
            int indexOfFoundBranch=-1;
            for(int i=0;i<arrayBranch.size();i++){
                if(arrayBranch.get(i).ifExist(name)){
                    indexOfFoundBranch=i;
                    break;
                }
            }
            arrayBranch.get(indexOfFoundBranch).addTransaction(name,newTransaction);
            System.out.println("Transaction added successfully");
        }else System.out.println("Client not found!");
    }

    public void printBranch(Branch newBranch){
            System.out.println("Printing "+newBranch.getClass().getSimpleName()+" branch");
            arrayBranch.get(arrayBranch.indexOf(newBranch)).print();
    }

    public void print(){
        System.out.println("Printing all branches from "+this.getClass().getSimpleName()+" bank");
        for(int i=0;i<arrayBranch.size();i++){
            this.printBranch(arrayBranch.get(i));
        }
    }

    public Branch getBranch(int indexBranch){
      return arrayBranch.get(indexBranch);
    }

    public int getArraySize(){
        return arrayBranch.size();
    }
}
