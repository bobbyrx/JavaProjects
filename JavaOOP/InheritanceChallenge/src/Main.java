public class Main {
    public static void main(String[] args) {
        TaxiCar taxi=new TaxiCar(3,2,3,100,"BMW","207","Ivan",4,1.80,4);
        taxi.print();
        taxi.speedRegulation(-20);
        taxi.changingGears(3);
        taxi.handSteering("Left");
        taxi.chargingMoney();
    }
}
