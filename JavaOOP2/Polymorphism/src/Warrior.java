public class Warrior extends ClassWow {
    private String weapon;

    public Warrior(String weapon) {
        super("Warrior", 13, 26, 16, "Mele DPS");
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Warrior stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println("Weapon equipped: "+this.getWeapon());
        System.out.println();
    }
}
