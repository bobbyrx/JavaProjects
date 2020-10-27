package com.company;

public class Main {

    public static void main(String[] args) {

        int myValue=10_000;
        int myMinIntValue=Integer.MIN_VALUE;
        int myMaxIntValue=Integer.MAX_VALUE;
        System.out.println("Integer Minimum Value ="+ myMinIntValue);
        System.out.println("Integer Maximum Value ="+ myMaxIntValue);
        System.out.println("Busted MAX value ="+ (myMaxIntValue+1));
        System.out.println("Busted MIN value ="+ (myMinIntValue-1));

        byte myMinByteValue=Byte.MIN_VALUE;
        byte myMaxByteValue=Byte.MAX_VALUE;
        System.out.println("Byte Minimum Value = "+myMinByteValue);
        System.out.println("Byte Maximum Value = "+myMaxByteValue);

        short myMinShortValue=Short.MIN_VALUE;
        short myMaxShortValue=Short.MAX_VALUE;
        System.out.println("Short Minimum Value = "+myMinShortValue);
        System.out.println("Short Maximum Value = "+myMaxShortValue);
        
        long myLongValue=100L; //L at the end of the number indicates it's a long type!!!

        long myMinLongValue=Long.MIN_VALUE;
        long myMaxLongValue=Long.MAX_VALUE;
        System.out.println("Long Minimum Value = "+myMinLongValue);
        System.out.println("Long Maximum Value = "+myMaxLongValue);

        long bigLongLiteralValue=2_147_483_647_234L;
        System.out.println("Big Long Literal Value = "+bigLongLiteralValue);

        int myTotal=(myMinIntValue/2);

        byte myNewByteValue=(byte)(myMinByteValue/2); //Casting is changing the type of the variable.

        short myNewShortValue=(short)(myMinShortValue/2);//It is required for short and byte to be casted!!!
    }
}
