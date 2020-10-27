package com.company;

public class Main {

    public static void main(String[] args) {
    	
	    float myMinFloatValue=Float.MIN_VALUE;
	    float myMaxFloatValue=Float.MAX_VALUE;
	    System.out.println("Float minimum value = "+myMinFloatValue);
	    System.out.println("Float maximum value = "+myMaxFloatValue);

		double myMinDoubleValue=Double.MIN_VALUE;
		double myMaxDoubleValue=Double.MAX_VALUE;
		System.out.println("Double minimum value = "+myMinDoubleValue);
		System.out.println("Double maximum value = "+myMaxDoubleValue);

		int myIntValue=5/2;
		float myFloatValue=(float)5/3;// If you use float and type it without "f" at the end
		//it will be a mistake. But only if the number is for example "3.4121";
		double myDoubleValue=5d/3d;//You can write "d" for double values at the end
		// It is a good practice!!! But you can use it without it!!!

		float mySecondFloatValue= 5.25f; //Just don't use float men!!!
		//use double instead!!! And above is called casting. So that it won't show a mistake;

		System.out.println("MyIntValue = "+myIntValue);
		System.out.println("MyFloatValue = "+myFloatValue);
		System.out.println("MyDoubleValue = "+myDoubleValue);

		double myPoundValue=1d;
		double myCalculationValue=0.45359237d;
		double myKilogramValue=(double)(myPoundValue*myCalculationValue);
		System.out.println("My result = "+myKilogramValue);
    }
}
