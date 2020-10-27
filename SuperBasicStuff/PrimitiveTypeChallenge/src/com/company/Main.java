package com.company;

public class Main {

    public static void main(String[] args) {
	    byte myByteNumber=120;
	    short myShortNumber=3000;
	   
	    byte maxSizeByte=Byte.MAX_VALUE;
	    byte minSizeByte=Byte.MIN_VALUE;
	    System.out.println("Max size byte = "+maxSizeByte);
	    System.out.println("Min size byte = "+minSizeByte);

        short maxSizeShort=Short.MAX_VALUE;
        short minSizeShort=Short.MIN_VALUE;
        System.out.println("Max size short = "+maxSizeShort);
        System.out.println("Min size short = "+minSizeShort);

        int myIntNumber=100_000;
        long myLongNumber=5000000000L+10L*(myByteNumber+myShortNumber+myIntNumber);

        //Type L at the end of the long number!!! It won't make an error
        // if the number is not bigger than the Int.MAX_VALUE. Otherwise it will!!!

        System.out.println("My Int Number = "+myIntNumber);
        System.out.println("My Long Number = "+myLongNumber);
    }
}
