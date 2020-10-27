public class Hunter extends ClassWow{
    private String petName;

    public Hunter(String petName) {
        super("Hunter", 15, 17, 23, "Ranged DPS");
        this.petName = petName;
    }

    public String getPetName() {
        return petName;
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Hunter stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println("Pet's name: "+this.getPetName());
        System.out.println();
    }
}
