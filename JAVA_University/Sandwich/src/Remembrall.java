public class Remembrall {
    public static void main(String[] args) {
        System.out.println(isPhoneNumberForgettable(""));
        System.out.println(isPhoneNumberForgettable("498-123-175"));
        System.out.println(isPhoneNumberForgettable("0894 123 563"));
        System.out.println(isPhoneNumberForgettable("(888)-FLOWERS"));
        System.out.println(isPhoneNumberForgettable("(444)-greens"));
    }
    public static boolean isPhoneNumberForgettable(String phoneNumber){
        if(phoneNumber==null||phoneNumber.length()==0)return false;
        String[] newString=phoneNumber.split("-");
        String[] otherString=phoneNumber.split(" ");
        int[] array=new int[10];
        if(otherString.length>newString.length){
            for(String value:otherString) {
                for(int i=0;i<value.length();i++){
                    if(value.charAt(i)<48||value.charAt(i)>57)return true;
                    else {
                        array[value.charAt(i)-48]++;
                    }
                }
            }
        }
        else{
            for(String value:newString){
                for(int i=0;i<value.length();i++){
                    if(value.charAt(i)<48||value.charAt(i)>57)return true;
                    else {
                        array[value.charAt(i)-48]++;
                    }
                }
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i]>1)return false;
        }
        return true;
    }
}
