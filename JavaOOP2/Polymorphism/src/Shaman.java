public class Shaman extends ClassWow {
    private String typeOfTotemEquipped;

    public Shaman( String typeOfTotemEquipped) {
        super("Shaman", 21, 17, 17, "Wildcard");
        this.typeOfTotemEquipped = typeOfTotemEquipped;
    }

    public String getTypeOfTotemEquipped() {
        return typeOfTotemEquipped;
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Shaman stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println("Type of totem equipped: "+this.getTypeOfTotemEquipped()+" totem");
        System.out.println();
    }
}
