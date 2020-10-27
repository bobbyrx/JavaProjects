public class Druid extends ClassWow{
    public Druid() {
        super("Druid", 20, 18, 17, "Wildcard");
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Druid stats:");
        System.out.println("Name of class: "+this.getName());
        System.out.println("Intellect: "+this.getIntellectStart());
        System.out.println("Strength: "+this.getStrengthStart());
        System.out.println("Agility: "+this.getAgilityStart());
        System.out.println("Role: "+this.getRole());
        System.out.println();
    }

    public void transformIntoBeast(String beast){
        System.out.println("Transforming into -> "+beast);
    }

}
