public class TaxiCar extends Car {
    private String nameOfDriver;
    private int doors;
    private double payment;
    private int distanceToDestination;

    public TaxiCar() {
        this.nameOfDriver = "Default name";
        this.doors = -1;
        this.payment = -1;
        this.distanceToDestination=0;
    }

    public TaxiCar(int size, int height, double weight, double speed, String brand, String model, String nameOfDriver, int doors, double payment,int distanceToDestination) {
        super(size, height, weight, speed, brand, model, 5, "Yellow");
        this.nameOfDriver = nameOfDriver;
        this.doors = doors;
        this.payment = payment;
        this.distanceToDestination=distanceToDestination;
    }

    public int getDistanceToDestination() {
        return distanceToDestination;
    }

    public void setDistanceToDestination(int distanceToDestination) {
        this.distanceToDestination = distanceToDestination;
    }

    public String getNameOfDriver() {
        return nameOfDriver;
    }

    public void setNameOfDriver(String nameOfDriver) {
        this.nameOfDriver = nameOfDriver;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void chargingMoney(){
        System.out.println("You should pay "+(payment*distanceToDestination)+"$");
    }

    public double money(){
        return this.getPayment()*this.getDistanceToDestination();
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Name of the driver: "+this.getNameOfDriver());
        System.out.println("Doors: "+this.getDoors());
        System.out.println("Payment: "+this.getPayment()+"$/km");
        System.out.println("Distance: "+this.getDistanceToDestination()+"km");
    }

    @Override
    public void handSteering(String turn) {
        System.out.println("The taxi is turning "+turn);
    }

}
