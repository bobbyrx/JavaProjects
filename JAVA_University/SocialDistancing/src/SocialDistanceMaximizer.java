public class SocialDistanceMaximizer {
    public static void main(String[] args) {
        System.out.println("Result: "+maxDistance(new int[] {1,0,0,1,0,1,0,1,0,0,1}));
        System.out.println("Result: "+maxDistance(new int[] {0,0,1,1,1,1,0,0,0,0}));
        System.out.println("Result: "+maxDistance(new int[] {1,0,0,0,0,0,0,0,0,0,0,0,1}));
        System.out.println("Result: "+maxDistance(new int[]{1, 0, 1, 0, 0}));
        System.out.println("Result: "+maxDistance(new int[]{1,0,0,0}));
        System.out.println("Result: "+maxDistance(new int[]{1, 1}));
    }
    public static int maxDistance(int[] seats){
        int count=0;
        for(int i=0;i<seats.length;i++){
            if(seats[i]==1||i==0||i==seats.length-1)count++;
        }
        int[] seatsIndex=new int[count];
        int countIndex=0;
        for(int i=0;i<seats.length;i++){
            if(seats[i]==1||i==0||i==seats.length-1) {
                seatsIndex[countIndex]=i;
                countIndex++;
            }
        }
        int maxDistance=0;
        int firstItem=-1;
        int secondItem=-1;
        for(int i=0;i<seatsIndex.length;i++) {
            if(i+1<seatsIndex.length) {
                firstItem=seats[seatsIndex[i]];
                secondItem=seats[seatsIndex[i+1]];
                int distance = seatsIndex[i+1] - seatsIndex[i];
                if(distance==1&&(secondItem==1&&firstItem==1))continue;
                if(firstItem==1&&secondItem==1)distance=distance/2;
                if (maxDistance < distance) maxDistance=distance;
            }
        }
        return maxDistance;
    }
}
