public class Contacts {
    private String name;
    private String numberPhone;

    public Contacts(String name, String numberPhone) {
        this.name = name;
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void printContacts(){
        System.out.println("Name: "+this.getName());
        System.out.println("Number of phone: "+this.getNumberPhone());
    }

    public boolean equal(Contacts newContact){
        return this.getName().equals(newContact.getName())
                && this.getNumberPhone().equals(newContact.getNumberPhone());
    }
}
