public class Car {

    private  int doors=0;
    private  int wheels=0;
    private  String model="";
    private  String engine="";
    private  String colour="";

    public Car()
    {
        this("Default model","Default engine","Default colour",0,0);
    }
    public Car(String model,String engine,String colour,int doors,int wheels)
    {   //It is better to not use setters in a constructor!!!
        //It's safer this way!!!
        this.colour=colour;
        this.model=model;
        this.doors=doors;
        this.wheels=wheels;
        this.engine=engine;
    }
    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public final void printCar()
    {
        System.out.println();
        System.out.println("Printing information:");
        System.out.println("Model: "+getModel());
        System.out.println("Engine: "+getEngine());
        System.out.println("Doors: "+getDoors());
        System.out.println("Wheels: "+getWheels());
        System.out.println("Colour: "+getColour());
        System.out.println();
    }


}
