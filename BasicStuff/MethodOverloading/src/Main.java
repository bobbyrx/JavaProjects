public class Main {
    public static void main(String[] args) {
        String hour=getDurationString(123,56);
        System.out.println("result: "+hour);
        String hour1=getDurationString(56);
        System.out.println("result: "+hour1);
        System.out.println(Math.PI);
    }
    public static String getDurationString(double minutes, double seconds)
    {
        if(minutes<0||seconds<0||seconds>60)return "Invalid value";

        long hour=(long)minutes/60;
        long min=(long)minutes%60;
        long sec=(long)seconds;
        return hour+"h "+min+"m "+sec+"s";
    }
    public static String getDurationString(double seconds)
    {
        if(seconds<0)return "Invalid value";

        long minutes=(long)seconds/60;
        long sec=(long)seconds%60;
        return getDurationString(minutes,sec);
    }
}
