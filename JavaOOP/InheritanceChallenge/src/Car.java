public class Car extends Vehicle {
    private String brand;
    private String model;
    private int seats;
    private String colour;

    public Car() {
        this.brand = "Default brand";
        this.model = "Default model";
        this.seats = -1;
        this.colour = "Default colour";
    }

    public Car(int size, int height, double weight, double speed, String brand, String model, int seats, String colour) {
        super("Car", 4, size, height, weight, speed);
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        this.colour = colour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void changingGears(int gear){
        System.out.println("Changing the gear to "+gear);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Brand: "+this.getBrand());
        System.out.println("Model: "+this.getModel());
        System.out.println("Seats: "+this.getSeats());
        System.out.println("Colour: "+this.getColour());
    }

    @Override
    public void handSteering(String turn) {
        System.out.println("The car is turning "+turn);
    }

}
