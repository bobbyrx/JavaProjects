public class Main {
    public static void main(String[] args)
    {
        final double NUM=36;
        calcFeetAndInchesToCentimeters(NUM);
    }
    public static double calcFeetAndInchesToCentimeters(double feet,double inches)
    {
        if(feet>=0||(inches>=0&&inches<=12))
        {
            System.out.println(feet+" feet, "+inches+" inches ="+(12*feet*2.54+inches*2.54)+"cm");
            return 12*feet*2.54+inches*2.54;
        }
        else return -1;
    }
    public static double calcFeetAndInchesToCentimeters(double inches)
    {
        if(inches>=0)
        {
            double feet=inches/12;
            return calcFeetAndInchesToCentimeters(feet,inches);
        }
        else return -1;
    }
}
