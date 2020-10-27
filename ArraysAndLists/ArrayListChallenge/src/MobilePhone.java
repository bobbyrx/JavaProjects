import java.util.ArrayList;

public class MobilePhone {
    private ArrayList<Contacts> contacts=new ArrayList<Contacts>();

    public int findElement(Contacts newContact){
        for(int i=0;i<contacts.size();i++){
            if(newContact.equal(contacts.get(i)))return i;
        }
        return -1;
    }

    public boolean ifExist(Contacts newContact){
        if(this.findElement(newContact)==-1)return false;
        else return true;
    }

    public void addContact(Contacts newContact){
        if(ifExist(newContact)){
            System.out.println("Already exist in the mobile phone.");
        }
        else {
            System.out.println("Added successfully into the mobile phone");
            contacts.add(newContact);
        }
    }

    public Contacts getContacts(int index){
        if(contacts.size()<index) {
            return null;
        }
        return contacts.get(index);
    }

    public void printContacts(){
        System.out.println("Printing all the contacts:");
        for(int i=0;i<contacts.size();i++){
            System.out.println("Contact["+(i+1)+"]: ");
            contacts.get(i).printContacts();
        }
    }

    public void updateContacts(int index,Contacts newContact){
        if(index>=contacts.size()) System.out.println("Sorry, you don't have that much contacts.");
        else if(this.ifExist(newContact)) System.out.println("Sorry, you already have a contact with this name and number!");
        else {
            System.out.println("Updated successfully");
            contacts.set(index,newContact);
        }
    }

    public void removeContacts(int index){
        if(index>=contacts.size()) System.out.println("Sorry, you don't have that much contacts.");
        else {
            System.out.println("Removed successfully.");
            contacts.remove(index);
        }
    }
}
