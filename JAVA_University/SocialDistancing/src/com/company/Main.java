package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Result: "+maxDistancing(new int[] {1,0,0,0,1,0,1}));
        System.out.println("Result: "+maxDistancing(new int[] {0,1,0,0}));
        System.out.println("Result: "+maxDistancing(new int[] {0,0,1,0,0,0,0,0,0,0,0,0,1}));
    }
    public static int maxDistancing(int[] array){
        int count=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==1||i==0||i==array.length-1)count++;
        }
        int[] arrayIndex=new int[count];
        int countIndex=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==1||i==0||i==array.length-1) {
                arrayIndex[countIndex]=i;
                countIndex++;
            }
        }
        int seat=-1;
        int maxDistance=0;
        for(int i=0;i<arrayIndex.length;i++) {
            if(i+1<arrayIndex.length) {
                int distance = arrayIndex[i + 1] - arrayIndex[i];
                if (maxDistance < distance) {
                    maxDistance = distance;
                    int distanceFromFirstPerson=0;
                    if (arrayIndex[i + 1] == array.length - 1 && array[array.length - 1] == 0) {
                        distanceFromFirstPerson = maxDistance;
                    }
                    else if(!(arrayIndex[i] == 0 && array[0] == 0))distanceFromFirstPerson = maxDistance / 2;

                    seat = arrayIndex[i] + distanceFromFirstPerson;
                }
            }
        }
        return seat;
    }
}
