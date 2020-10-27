package com.company;

import org.w3c.dom.ls.LSOutput;

public class Main {

    public static void main(String[] args) {

        boolean isAlian=false;
        if(isAlian==true)
        {
            System.out.println("Oh no!!!");
        }
        else
        {
            System.out.println("Yea I knew it!");
        }
        boolean isCar =false;
        if(isCar=true)//Here the boolean is assigned to be true!!!
            //It does not ask if the boolean is true!!!
        {
            System.out.println("This is not supposed to happen");
        }
        if(!isCar)//Because isCar became true above!!! And you can do this in c++ too!!!
        {
            System.out.println("Hello");
        }
        boolean wasCar =isCar ? false: true; //OK, this you can do in c++ too!!!
        //Very useful!!!
        System.out.println("WasCar = "+wasCar);
        //Some other useful things!!!
        long ageOfClient=20;
        boolean isEighteenOrOVer= ageOfClient>=20 ? true: false; //This is called Ternary operator
        if(isEighteenOrOVer)
        {
            System.out.println("Yes he is!");
        }
        long testIsHere= ageOfClient>=18 ? 10 : 0;//Just testing it!!!
        if(testIsHere==10)
        {
            System.out.println("He is having test soon!!!");
        }
        else System.out.println(4);

        System.out.println(testIsHere==10 ? "Hello there":4);//Equal to the above!!!
    }
}

