public class Paladin extends ClassWow {
    public Paladin() {
        super("Paladin", 23, 17, 15,"Mele Support" );
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Paladin stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println();
    }

    public void buffParty(String blessing){
        System.out.println("Your party is buffed with -> "+blessing);
    }

}
