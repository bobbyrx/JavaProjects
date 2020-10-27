package com.company;

public class Main {

    public static void main(String[] args) {

        char myChar='D';
        char myUnicodeChar='\u0044';//This is D!!!
        System.out.println("Mychar = "+myChar);
        System.out.println("MyUnicodechar = "+myUnicodeChar);
        char myCopyrightChar='\u00A9';
        System.out.println(myCopyrightChar);
        boolean myTrueBooleanValue=true;
        boolean myFalseBooleanValue=false;

        boolean isCustomerOverTwentyOne=true;
        System.out.println("Customer is over twenty-one = "+isCustomerOverTwentyOne);
    }
}
