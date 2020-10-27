public class Dog extends Animal{

    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    public Dog() {
        this(new Animal(),0,0,0,0,"Default coat");
    }

    public Dog(String name, int brain, int body, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        super(name, brain, body, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    public Dog(Animal animal,int eyes,int legs,int tail,int teeth,String coat) {
        this(animal.getName(),animal.getBrain(),animal.getBody(),
                animal.getSize(),animal.getWeight(),eyes,legs,tail,teeth,coat);
    }

    public Dog(int eyes,int legs,int tail,int teeth,String coat){
        this(new Animal(),eyes,legs,tail,teeth,coat);
    }

    public Dog(Dog dog) {
        this(dog.getName(),dog.getBrain(),dog.getBody(),dog.getSize(),dog.getWeight(),dog.getEyes(),dog.getLegs(),dog.getTail(),dog.getTeeth(),dog.getCoat());
    }

    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getTeeth() {
        return teeth;
    }

    public void setTeeth(int teeth) {
        this.teeth = teeth;
    }

    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    @Override
    public void eat() {
        //super.eat();
        System.out.println();
        System.out.println("The dog eats.");
        System.out.println();
    }

    @Override
    public void move() {
        //super.move();
        System.out.println();
        System.out.println("The dog moves.");
        System.out.println();
    }

    @Override
    public void printAnimal() {
        System.out.println();
        System.out.println("Printing dog Info:");
        super.printAnimal();
        System.out.println("Eyes: "+this.getEyes());
        System.out.println("Legs: "+this.getLegs());
        System.out.println("Tail: "+this.getTail());
        System.out.println("Teeth: "+this.getTeeth());
        System.out.println("Coat: "+this.getCoat());
        System.out.println();
    }

    @Override
    public Dog copy() {
        return new Dog(this);
    }

}
