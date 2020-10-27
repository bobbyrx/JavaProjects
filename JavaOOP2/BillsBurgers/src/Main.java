public class Main {
    public static void main(String[] args) {
        Tomato tomato=new Tomato();
        Onion onion=new Onion();
        Cheese cheese=new Cheese();
        Lettuce lettuce=new Lettuce();
        Hamburger hamburger=new Hamburger("BigMac","Normal","Beef");
        hamburger.addAddition(tomato);
        hamburger.addAddition(cheese);
        hamburger.addAddition(lettuce);
        hamburger.addAddition(onion);
        hamburger.print();
        HealthyBurger healthyBurger=new HealthyBurger("MacChicken","Chicken");
        healthyBurger.addAddition(tomato);
        healthyBurger.addAddition(cheese);
        healthyBurger.addAddition(cheese);
        healthyBurger.addAddition(lettuce);
        healthyBurger.addAddition(onion);
        healthyBurger.addAddition(onion);
        healthyBurger.print();
        DeluxeHamburger deluxeHamburger=new DeluxeHamburger("Deluxanator","Black bread","Supreme meat");
        deluxeHamburger.print();
    }
}
