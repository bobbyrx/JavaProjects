public class Main {
    public static void main(String[] args)
    {
        printSquareStar(19);
    }

        public static void printSquareStar(int number)
        {
            if(number<5)System.out.println("Invalid Value");
            else
            {
                int leftStar=0;
                int rightStar=number-1;
                for(int i=0;i<number;i++)
                {
                    for(int j=0;j<number;j++)
                    {
                        if(i==0||i==number-1)
                        {
                            System.out.print("*");
                        }
                        else if(j==0||j==number-1)System.out.print("*");
                        else if(j==leftStar||j==rightStar)System.out.print("*");
                        else System.out.print(" ");
                    }
                    System.out.println();
                    leftStar++;
                    rightStar--;
                }
            }
        }
}
