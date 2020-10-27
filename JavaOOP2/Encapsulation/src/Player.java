public class Player {
   private String name;
   private int hitPoint;
   private String weapon;

   public Player(){
       this("Default name",100,"Default weapon");
   }

    public Player(String name, int hitPoint, String weapon) {
        this.name = name;
        if(hitPoint >=0&& hitPoint <=100)this.hitPoint = hitPoint;
        this.weapon = weapon;
    }

    public void loseHealth(int damage){
       this.hitPoint -=damage;
       if(this.hitPoint <=0) {
           this.hitPoint =0;
           System.out.println("Player knocked out!");
       }
    }

    public Player copy(){
        return new Player(this.getName(),this.getHitPoint(),this.getWeapon());
    }

    protected void setName(String name) {
        this.name = name;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHitPoint() {
        return this.hitPoint;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getName() {
        return name;
    }
    public void print(){
        System.out.println();
        System.out.println("Printing info:");
        System.out.println("Name: "+this.getName());
        System.out.println("Hit points: "+this.getHitPoint());
        System.out.println("Weapon: "+this.getWeapon());
    }
}
