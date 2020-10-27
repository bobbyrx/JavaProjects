public class Rogue extends ClassWow{
    public Rogue() {
        super("Rogue", 13, 15, 27, "Mele DPS");
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Rogue stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println();
    }

    public void getIntoStealth(){
        System.out.println("Stepping into the shadows");
    }
}
