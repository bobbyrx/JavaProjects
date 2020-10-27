public class Point {
    private int x;
    private int y;

    public Point()
    {
     this(0,0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distanceWithoutAnyParameters()
    {
        return Math.sqrt((-x)*(-x)+(-y)*(-y));
    }

    public double distanceWithTwoParameters(int x,int y)
    {
        return Math.sqrt((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y));
    }

    public double distanceWithParameter(Point otherPoint)
    {
        return Math.sqrt((otherPoint.getX()-x)*(otherPoint.getX()-x)
                +(otherPoint.getY()-y)*(otherPoint.getY()-y));
    }

}
