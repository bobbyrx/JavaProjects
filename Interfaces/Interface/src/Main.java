public class Main {
    public static void main(String[] args){
        ITelephone timePhone;
        timePhone=new DeskPhone(123456);
        timePhone.powerOn();
        timePhone.callPhone(123456);
        timePhone.answer();

        timePhone=new MobilePhone(24565);
        timePhone.powerOn();
        timePhone.callPhone(24565);
        timePhone.answer();
    }
}
