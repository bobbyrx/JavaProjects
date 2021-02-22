package bg.sofia.uni.fmi.mjt.restaurant;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Chef extends Thread {

    private final int id;
    private final Restaurant restaurant;
    private AtomicInteger mealsCooked = new AtomicInteger(0);
    private AtomicBoolean isOpen = new AtomicBoolean(true);

    public int chefId() {
        return this.id;
    }

    public void terminate() {
        isOpen.set(false);
    }

    public Chef(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (true) {
            Order newOrder;
            synchronized (this.restaurant) {
                newOrder = this.restaurant.nextOrder();
                if (newOrder != null) {
                    System.out.println("Chef " + this.chefId()
                            + " needs " + newOrder.meal().getCookingTime()
                            + " minutes to cook " + newOrder.meal().getName() + " for "
                            + "customer " + newOrder.customer().getId());
                    mealsCooked.getAndIncrement();
                }
            }
            if (newOrder != null) {
                try {
                    this.sleep(newOrder.meal().getCookingTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Chef " + this.chefId()
                        + " made " + newOrder.meal().getName() + " for "
                        + "customer " + newOrder.customer().getId());
            }
            if (!isOpen.get() && newOrder == null) {
                break;
            }
        }

        //do {
        //    chefWorking();
        //} while (isOpen.get() || this.restaurant.getOrdersCount() > 0);
    }

    /**
     * Returns the total number of meals that this chef has cooked.
     **/
    public int getTotalCookedMeals() {
        return mealsCooked.get();
    }

    //private void chefWorking(){
    //    try {
    //        synchronized (this.restaurant) {
    //            while (this.restaurant.getOrdersCount() == 0 && isOpen.get()) {
    //                System.out.println("Chef " + this.chefId() + " waits!");
    //                this.restaurant.wait();
    //            }
    //            Order newOrder=this.restaurant.nextOrder();
    //            if (newOrder != null) {
    //                System.out.println("Chef " + this.chefId()
    //                        + " needs " + newOrder.meal().getCookingTime()
    //                        + " minutes to cook " + newOrder.meal().getName() + " for "
    //                        + "customer " + newOrder.customer().getId());
    //                mealsCooked.getAndIncrement();
    //                sleep(newOrder.meal().getCookingTime());
    //                System.out.println("Chef " + this.chefId()
    //                        + " made " + newOrder.meal().getName() + " for "
    //                        + "customer " + newOrder.customer().getId());
    //            }
    //        }
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //}

}