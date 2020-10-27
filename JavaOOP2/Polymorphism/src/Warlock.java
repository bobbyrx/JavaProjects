public class Warlock extends ClassWow {
    public Warlock() {
        super("Warlock", 27, 14, 14, "Ranged DPS");
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Warlock stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println();
    }

    public void summonDemon(String demon){
        System.out.println("Summoning the -> "+demon);
    }
}
