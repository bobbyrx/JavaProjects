package com.company;

public class Main {

    public static void main(String[] args) {

        double myDoubleValue=20.00d;
        double mySecondDoubleValue=80.00d;
        System.out.println("Result = "+(double)((myDoubleValue+mySecondDoubleValue)*100.00d));
        double myResult=(myDoubleValue+mySecondDoubleValue)*100.00d;
        double myRemainder=myResult%40.00d;
        System.out.println("My Remainder = "+myRemainder);
        boolean isARemainder= myRemainder==0 ?true:false;
        System.out.println(isARemainder==false? "Got some remainder.":"Don't have a remainder.");

    }
}
