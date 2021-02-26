package bg.sofia.uni.fmi.mjt.restaurant;

import bg.sofia.uni.fmi.mjt.restaurant.customer.AbstractCustomer;
import bg.sofia.uni.fmi.mjt.restaurant.customer.Customer;
import bg.sofia.uni.fmi.mjt.restaurant.customer.VipCustomer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MJTDiningPlaceTest {

    private final List<AbstractCustomer> listOfCustomers = new ArrayList<>();
    private static final Random RANDOM = new Random();

    @Test
    public void restaurantTest() throws InterruptedException {
        MJTDiningPlace restaurant = new MJTDiningPlace(1_000);
        System.out.println("Restaurant is opened!");

        final int NUMBER_OF_CUSTOMERS = 50_000;

        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {

            AbstractCustomer customer;
            int integer = RANDOM.nextInt(2);

            switch (integer) {
                case 0:
                    customer = new Customer(restaurant);
                    System.out.println("Ordinary Customer " + customer.getId()
                            + " enters in the restaurant");
                    listOfCustomers.add(customer);
                    customer.start();
                    break;
                case 1:
                    customer = new VipCustomer(restaurant);
                    System.out.println("Vip Customer " + customer.getId()
                            + " enters in the restaurant");
                    listOfCustomers.add(customer);
                    customer.start();
                    break;
                default:
                    break;
            }
        }

        Thread.sleep(100);
        restaurant.close();
        System.out.println("Restaurant is closing!");

        for (AbstractCustomer customer : listOfCustomers) {
            customer.join();
            // System.out.println("Customer " + customer.getId() + " leaves the restaurant!");
        }

        for (Chef newChef : restaurant.getChefs()) {
            newChef.join();
            // System.out.println("Chef " + newChef.chefId() + "'s work is over and is going home!");
        }

        System.out.println("Restaurant is closed!");

        int count = 0;
        for (Chef newChef : restaurant.getChefs()) {
            System.out.println("Chef " + newChef.chefId() + " cooked total of "
                    + newChef.getTotalCookedMeals() + " meals!");
            count += newChef.getTotalCookedMeals();
        }
        System.out.println("Total of " + count + " orders where made");
        assertEquals("It should be equal!", count, restaurant.getOrdersCount());
        assertEquals("There is a problem with compareTo", 1, restaurant.compareTo(true, false));
        assertEquals("There is a problem with compareTo", 1, restaurant.compareTo(2, 1));
    }
}
