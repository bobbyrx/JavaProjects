public class Mage extends ClassWow {
    public Mage() {
        super("Mage", 29, 13, 13, "Ranged DPS");
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Mage stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println();
    }
    public void conjureWaterAndFood(int numberOfFriends){
        System.out.println("Conjured -> "+numberOfFriends+" food and water");
    }
}
