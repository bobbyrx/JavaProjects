public class Animal {
    private String name;
    private int brain;
    private int body;
    private int size;
    private int weight;

    public Animal() {
        this("Default name",0,0,0,0);
    }

    public Animal(String name, int brain, int body, int size, int weight) {
        this.name = name;
        this.brain = brain;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    public Animal(Animal animal) {
        this(animal.getName(),animal.getBrain(),animal.getBody(),animal.getSize(),animal.getWeight());
    }

    public void eat(){
        System.out.println();
        System.out.println("The animal eats.");
        System.out.println();
    }

    public void move(){
        System.out.println();
        System.out.println("The animal moves.");
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrain() {
        return brain;
    }

    public void setBrain(int brain) {
        this.brain = brain;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void printAnimal() {
        System.out.println("Name: "+this.getName());
        System.out.println("Brain: "+this.getBrain());
        System.out.println("Body: "+this.getBody());
        System.out.println("Size: "+this.getSize());
        System.out.println("Weight: "+this.getWeight());
    }

  public Animal copy() {
        return new Animal(this);
  }


}
