package bg.sofia.uni.fmi.mjt.restaurant;

import bg.sofia.uni.fmi.mjt.restaurant.customer.AbstractCustomer;
import bg.sofia.uni.fmi.mjt.restaurant.customer.Customer;
import bg.sofia.uni.fmi.mjt.restaurant.customer.VipCustomer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MJTDiningPlaceTest {

    private final List<AbstractCustomer> listOfCustomers = new ArrayList<>();

    @Test
    public void restaurantTest() throws InterruptedException {
        Restaurant restaurant = new MJTDiningPlace(100);
        System.out.println("Restaurant is opened!");

        int numberOfCustomers = 250;
        for (int i = 0; i < numberOfCustomers; i++) {
            AbstractCustomer ordinaryCustomer = new Customer(restaurant);
            if (ordinaryCustomer.hasVipCard()) {
                System.out.println("Vip Customer " + ordinaryCustomer.getId()
                        + " enters in the restaurant");
            } else {
                System.out.println("Ordinary Customer " + ordinaryCustomer.getId()
                        + " enters in the restaurant");
            }
            listOfCustomers.add(ordinaryCustomer);
            AbstractCustomer vipCustomer = new VipCustomer(restaurant);
            if (vipCustomer.hasVipCard()) {
                System.out.println("Vip Customer " + vipCustomer.getId() + " enters in the restaurant");
            } else {
                System.out.println("Ordinary Customer " + vipCustomer.getId() + " enters in the restaurant");
            }
            listOfCustomers.add(vipCustomer);
            ordinaryCustomer.start();
            vipCustomer.start();
        }

        //Thread.sleep(100);
        restaurant.close();
        System.out.println("Restaurant is closing!");

        for (AbstractCustomer customer : listOfCustomers) {
            customer.join();
            System.out.println("Customer " + customer.getId() + " leaves the restaurant!");
        }

        for (Chef newChef : restaurant.getChefs()) {
            System.out.println("Chef " + newChef.chefId() + "'s work is over and is going home!");
        }

        System.out.println("Restaurant is closed!");

        int count = 0;
        for (Chef newChef : restaurant.getChefs()) {
            System.out.println("Chef " + newChef.chefId() + " cooked total of "
                    + newChef.getTotalCookedMeals() + " meals!");
            count += newChef.getTotalCookedMeals();
        }
        System.out.println("Total of "+count+" orders where made");
        assertEquals("It should be equal!", 0, restaurant.getOrdersCount());
        assertEquals("There is a problem with compareTo", 1, ((MJTDiningPlace) restaurant).compareTo(true, false));
        assertEquals("There is a problem with compareTo", 1, ((MJTDiningPlace) restaurant).compareTo(2, 1));
    }
}
