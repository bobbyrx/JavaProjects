public class Floor {
    private double wight;
    private double length;

    public Floor(double wight, double length) {
        if(wight<0)this.wight=0;
        else this.wight = wight;
        if(length<0)this.length=0;
        else this.length = length;
    }

    public double getWight() {
        return wight;
    }

    public void setWight(double wight) {
        if(wight<0)this.wight=0;
        else this.wight = wight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length < 0) this.length = 0;
        else this.length = length;
    }

    public double getArea()
    {
        return wight*length;
    }

}
