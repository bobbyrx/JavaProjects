package bg.sofia.uni.fmi.mjt.restaurant.customer;

import bg.sofia.uni.fmi.mjt.restaurant.Meal;
import bg.sofia.uni.fmi.mjt.restaurant.Order;
import bg.sofia.uni.fmi.mjt.restaurant.Restaurant;

public abstract class AbstractCustomer extends Thread {

    private final Restaurant restaurant;

    public AbstractCustomer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this.restaurant) {
            Order newOrder = new Order(Meal.chooseFromMenu(), this);
            System.out.println("Customer " + this.getId() + " orders " + newOrder.meal().getName());
            this.restaurant.submitOrder(newOrder);
            //this.restaurant.notifyAll();
        }
    }

    public abstract boolean hasVipCard();

}