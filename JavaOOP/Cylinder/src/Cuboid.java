public class Cuboid extends Rectangle {
    private double width;
    private double length;
    private double height;

    public Cuboid(double width, double length, double width1, double length1, double height) {
        super(width, length);
        this.width = width1;
        this.length = length1;
        if(height<0)this.height=0;
        else this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height<0)this.height=0;
        else this.height = height;
    }

    public double getVolume(){
        return this.getArea()*this.getHeight();
    }

}
