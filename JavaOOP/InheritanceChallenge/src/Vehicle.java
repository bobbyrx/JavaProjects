public class Vehicle {
    private String type;
    private int wheels;
    private int size;
    private int height;
    private double weight;
    private double speed;

    public Vehicle() {
        this("Default type",-1,-1,-1,-1,-1);
    }

    public Vehicle(String type, int wheels, int size, int height, double weight, double speed) {
        this.type = type;
        this.wheels = wheels;
        this.size = size;
        this.height = height;
        this.weight = weight;
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if(speed<0)this.speed=0;
        else this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void print()
    {
        System.out.println("Type: "+this.getType());
        System.out.println("Wheels: "+this.getWheels());
        System.out.println("Size: "+this.getSize());
        System.out.println("Height: "+this.getHeight()+"m");
        System.out.println("Weight: "+this.getWeight()+"t");
        System.out.println("Speed: "+this.getSpeed()+"km/h");
    }

    public void handSteering(String turn) {
        System.out.println("The vehicle is turning "+turn);
    }

    public void speedRegulation(double speed) {
        double speedCopy=this.getSpeed();
        speedCopy+=speed;
        this.setSpeed(speedCopy);
        System.out.println("Speed regulated with "+speed+"km/h");
        System.out.println("Speed now is "+this.getSpeed()+"km/h");
    }

}
