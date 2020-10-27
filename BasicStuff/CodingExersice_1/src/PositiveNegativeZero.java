public class PositiveNegativeZero
{
    private int num;
    public void setNumber(int number)
    {
        num=number;
    }
    public int getNumber()
    {
        return num;
    }
    public void checkNumber ()
    {
        if(num>0)
        {
            System.out.println("positive");
        }
        else if(num<0)
        {
            System.out.println("negative");
        }
        else
        {
            System.out.println("zero");
        }

    }
}
