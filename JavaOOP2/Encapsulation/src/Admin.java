public class Admin extends Player {
    private int level;
    private String classPlayer;

    public Admin(int level, String classPlayer) {
        this("Default name",level,classPlayer);
    }

    public Admin() {
        this("Default name",0,"Default class");
    }

    public Admin(String name, int level, String classPlayer) {
        super(name, 100, "Axe");
        this.level = level;
        this.classPlayer = classPlayer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClassPlayer() {
        return classPlayer;
    }

    public void setClassPlayer(String classPlayer) {
        this.classPlayer = classPlayer;
    }

    public void changeHealthPlayer(Player player){
        player.setHitPoint(25);
        System.out.println("Changed hit points of "+player.getName());
    }

    @Override
    public Admin copy() {
        Player copy=super.copy();
        return new Admin(copy.getName(),this.getLevel(),this.getClassPlayer());
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Level: "+this.getLevel());
        System.out.println("Class: "+this.getClassPlayer());
        System.out.println();
    }
}
