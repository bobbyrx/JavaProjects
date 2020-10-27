public class Main
{
    public static void main(String[] args)
    {
         int number;
         number=11;
         PositiveNegativeZero check=new PositiveNegativeZero();
         check.setNumber(number);
         System.out.println(check.getNumber());
         check.setNumber(number+1);
         System.out.println(check.getNumber());
         check.setNumber(number+1);
         System.out.println(check.getNumber());
         System.out.println(number);
    }
}
